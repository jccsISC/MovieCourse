package com.jccsisc.jpelisc.ui.fragments.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.jccsisc.jpelisc.R
import com.jccsisc.jpelisc.core.MyResource
import com.jccsisc.jpelisc.data.local.AppDataBase
import com.jccsisc.jpelisc.data.local.LocalMovieDataSource
import com.jccsisc.jpelisc.data.model.Movie
import com.jccsisc.jpelisc.data.remote.RemoteMovieDataSource
import com.jccsisc.jpelisc.databinding.FragmentMovieBinding
import com.jccsisc.jpelisc.presentation.MovieViewModel
import com.jccsisc.jpelisc.presentation.MovieViewModelFactory
import com.jccsisc.jpelisc.repository.MovieRepositoryImpl
import com.jccsisc.jpelisc.repository.RetrofitClient
import com.jccsisc.jpelisc.ui.fragments.movie.adapter.MovieAdapter
import com.jccsisc.jpelisc.ui.fragments.movie.adapter.concat.PopularConcatAdapter
import com.jccsisc.jpelisc.ui.fragments.movie.adapter.concat.TopRatedConcatAdapter
import com.jccsisc.jpelisc.ui.fragments.movie.adapter.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webService),
                LocalMovieDataSource(AppDataBase.getDataBase(requireContext()).movieDao())
            )
        )
    }
    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter() //inicializamos instancia vacia

        binding.apply {
            // los parametros para observer solo en fragments viewLifecyclerOwner para tener solo un observer
            viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
                when(result) {
                    is MyResource.Loading -> {
                        progress.visibility = View.VISIBLE
                    }
                    is MyResource.Success -> {
                        progress.visibility = View.GONE
                       //aqui le damos el orden de mostrar las listas
                        concatAdapter.apply {
                            addAdapter(0, UpcomingConcatAdapter(MovieAdapter(result.data.first.results, this@MovieFragment)))
                            addAdapter(1, PopularConcatAdapter(MovieAdapter(result.data.second.results, this@MovieFragment)))
                            addAdapter(2, TopRatedConcatAdapter(MovieAdapter(result.data.third.results, this@MovieFragment)))
                        }
                        rvMovies.adapter = concatAdapter
                    }
                    is MyResource.Failure -> {
                        progress.visibility = View.GONE
                        Log.d("ERROR", "${result.exception}")
                    }
                }
            })
        }
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }

}
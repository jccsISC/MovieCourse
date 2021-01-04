package com.jccsisc.jpelisc.ui.fragments.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jccsisc.jpelisc.R
import com.jccsisc.jpelisc.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieDetailBinding.bind(view)

        binding.apply {
            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}").centerCrop().into(imgMovie)
            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backgroundImageUrl}").centerCrop().into(imgBackground)
            tvDescription.text = args.overView
            tvTitle.text = args.title
            tvLanguage.text = "${resources.getString(R.string.language)} ${args.language}"
            tvRating.text = "${args.voteAverage} (${args.voteCount} Reviews)"
            tvReleased.text = "Releaded ${args.releaseData}"
        }
    }
}
package com.jccsisc.jpelisc.ui.fragments.movie.adapter.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jccsisc.jpelisc.core.BaseConcatHolder
import com.jccsisc.jpelisc.databinding.PopularMovieRowBinding
import com.jccsisc.jpelisc.ui.fragments.movie.adapter.MovieAdapter

//en lugar de pasar una lista de elementos vamos a pasarle el adapter de como queremos mostrar los elementos
class PopularConcatAdapter(private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = PopularMovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder) {
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }


    override fun getItemCount(): Int = 1 //porque tenemos solo un adapter

    private inner class ConcatViewHolder(val binding: PopularMovieRowBinding): BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvPopulargMovies.adapter = adapter
        }
    }
}
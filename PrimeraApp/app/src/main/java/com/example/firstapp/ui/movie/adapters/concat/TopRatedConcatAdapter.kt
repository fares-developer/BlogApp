package com.example.firstapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.core.BaseConcatHolder
import com.example.firstapp.databinding.TopRatedMoviesRowBinding
import com.example.firstapp.ui.movie.adapters.MovieAdapter

class TopRatedConcatAdapter (
    private val moviesAdapter: MovieAdapter
) : RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = TopRatedMoviesRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    //Es 1 porque no le pasamos una lista sino un adapter
    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(
        val binding: TopRatedMoviesRowBinding
    ) : BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvTopRatedMovies.adapter = adapter
        }
    }
}
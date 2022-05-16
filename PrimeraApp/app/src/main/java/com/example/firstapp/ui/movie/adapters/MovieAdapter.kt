package com.example.firstapp.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstapp.core.BaseViewHolder
import com.example.firstapp.data.model.Movie
import com.example.firstapp.databinding.MovieItemBinding

//Este será el adaptador principal que se encarge inflar cada sección
class MovieAdapter(
    private val movieList: List<Movie>,
    private val itemClickListener: OnMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    //Interfa para controlar cuando hacemos click en una película
    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }


    //Buscamos la interfaz que va a inflar cada uno de los elementos de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)

        //Ahorra necesitamos un método que nos devuelva la película que hemos hecho click
        itemBinding.root.setOnClickListener {
            //Obtenemos la position de la pelicula
            val position = holder.bindingAdapterPosition
                .takeIf { it != DiffUtil.DiffResult.NO_POSITION } ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size

    private inner class MoviesViewHolder(
        val binding: MovieItemBinding,
        val context: Context
    ) : BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + item.poster_path)
                .centerCrop().into(binding.imageMovie)
        }
    }
}
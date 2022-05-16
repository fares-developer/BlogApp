package com.example.firstapp.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${args.posterImageUrl}")
            .centerCrop().into(binding.imgMovie)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500${args.backgroundImageUrl}")
            .centerCrop().into(binding.imgBackground)

        binding.txtDescription.text = args.overview
        binding.movieTitle.text = args.title
        binding.txtLanguage.text = "Language ${args.language}"
        binding.txtRating.text = "${args.voteAverage} (${args.voteCount} Reviews)"
        binding.txtRelaease.text = "Released ${args.releaseDate}"
    }
}
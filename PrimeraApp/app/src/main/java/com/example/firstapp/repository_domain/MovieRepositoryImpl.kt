package com.example.firstapp.repository_domain

import com.example.firstapp.data.model.MovieList
import com.example.firstapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSourceRemote:RemoteMovieDataSource) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList = dataSourceRemote.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSourceRemote.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSourceRemote.getPopularMovies()
}
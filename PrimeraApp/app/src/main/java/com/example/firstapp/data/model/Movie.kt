package com.example.firstapp.data.model

import com.google.gson.annotations.SerializedName

//Cada uno de estos parámetros son exactamente los que nos vienen de la API
data class Movie(
    val id: Int = -1,
    @SerializedName("adult")//Serialize me permite utilizar el nombre que quiera pero trayendo el del servidor
    val adultos: Boolean = false,
    val genre_ids: List<Int> = listOf(),
    val backdrop_path: String = "",
    val original_title: String = "",
    val original_language: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = -1.0,
    val vote_count: Int = -1
)

data class MovieList(val results:List<Movie> = listOf())
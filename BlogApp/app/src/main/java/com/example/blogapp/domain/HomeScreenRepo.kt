package com.example.blogapp.domain

import com.example.blogapp.core.Resource
import com.example.blogapp.data.model.Post

//Esta interfaz va a contener un método que va a ir a buscar información al servidor
interface HomeScreenRepo {

    suspend fun getLatestPosts(): Resource<List<Post>>
}
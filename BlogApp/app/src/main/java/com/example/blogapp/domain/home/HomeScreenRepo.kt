package com.example.blogapp.domain.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import kotlinx.coroutines.flow.Flow

//Esta interfaz va a contener un método que va a ir a buscar información al servidor
interface HomeScreenRepo {

    suspend fun getLatestPosts(): Result<List<Post>>

    suspend fun registerLikeButtonState(postId:String,liked:Boolean)
}
package com.example.blogapp.domain.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.example.blogapp.data.remote.home.HomeScreenDataSource

//Esta clase implementa la interfaz HomeScreenRepo
class HomeScreenRepoImp(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    override suspend fun getLatestPosts(): Result<List<Post>> = dataSource.getLatestPosts()
}
package com.example.blogapp.domain

import com.example.blogapp.core.Resource
import com.example.blogapp.data.model.Post
import com.example.blogapp.data.remote.HomeScreenDataSource

//Esta clase implementa la interfaz HomeScreenRepo
class HomeScreenRepoImp(private val dataSource: HomeScreenDataSource):HomeScreenRepo {

    override suspend fun getLatestPosts(): Resource<List<Post>> = dataSource.getLatestPosts()
}
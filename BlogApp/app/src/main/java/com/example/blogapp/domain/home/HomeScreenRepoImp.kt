package com.example.blogapp.domain.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.example.blogapp.data.remote.home.HomeScreenDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

//Esta clase implementa la interfaz HomeScreenRepo
class HomeScreenRepoImp(private val dataSource: HomeScreenDataSource): HomeScreenRepo {

    @ExperimentalCoroutinesApi
    override suspend fun getLatestPosts(): Flow<Result<List<Post>>> = dataSource.getLatestPosts()
}
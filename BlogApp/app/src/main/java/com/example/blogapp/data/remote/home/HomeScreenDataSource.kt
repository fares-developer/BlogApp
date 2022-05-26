package com.example.blogapp.data.remote.home

import com.example.blogapp.core.Resource
import com.example.blogapp.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

//En esta clase estará el método que irá a buscar la info a Firebase
class HomeScreenDataSource {

    suspend fun getLatestPosts():Resource<List<Post>>{
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("post").get().await()

        for (post in querySnapshot.documents) {
            //Transformamos el snapshot post a un Post objeto y lo añadimos a la lista de Post
            post.toObject(Post::class.java)?.let { postList.add(it) }
        }

        return Resource.Success(postList)
    }
}
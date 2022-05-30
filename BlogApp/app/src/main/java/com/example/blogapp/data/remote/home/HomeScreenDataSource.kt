package com.example.blogapp.data.remote.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

//En esta clase estará el método que irá a buscar la info a Firebase
class HomeScreenDataSource {

    suspend fun getLatestPosts(): Result<List<Post>> {
        val postList = mutableListOf<Post>()

        withContext(Dispatchers.IO) {
            val querySnapshot = FirebaseFirestore.getInstance()
                .collection("posts")
                .orderBy("create_at", Direction.DESCENDING).get().await()

            for (post in querySnapshot.documents) {
                //Transformamos el snapshot post a un Post objeto y lo añadimos a la lista de Post
                post.toObject(Post::class.java)?.let {

                    it.apply {
                        //Obtengo un timeStamp estimado del servidor para que se nos muestre directamente
                        // al publicar el post
                        create_at = post.getTimestamp(
                            "create_at", DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                        )?.toDate()
                    }
                    postList.add(it)
                }
            }
        }

        return Result.Success(postList)
    }
}
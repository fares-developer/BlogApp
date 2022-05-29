package com.example.blogapp.data.remote.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Query.Direction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

//En esta clase estará el método que irá a buscar la info a Firebase
class HomeScreenDataSource {

    //Ahorra utilizaremos los flujos de kotlin
    @ExperimentalCoroutinesApi
    suspend fun getLatestPosts(): Flow<Result<List<Post>>> = callbackFlow {
        val postList = mutableListOf<Post>()

        var postReference: Query? = null

        try {
            postReference = FirebaseFirestore.getInstance()
                .collection("posts").orderBy("create_at", Direction.DESCENDING)
        } catch (e: Throwable) {
            close(e)
        }

        //Vamos a estar escuchando si hay datos nuevos en el servidor y los ofrecermos abajo
        val suscription = postReference?.addSnapshotListener { value, error ->
            if (value == null) return@addSnapshotListener

            try {
                postList.clear()
                for (post in value.documents) {
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
            } catch (e: Exception) {
                close(e)
            }
            // offer(Result.Success(postList)) //Ofrecemos al flow una lista de post
            trySend(Result.Success(postList)).isSuccess//Ofrecemos al flow una lista de post

        }
        awaitClose { suscription?.remove() }
    }
}
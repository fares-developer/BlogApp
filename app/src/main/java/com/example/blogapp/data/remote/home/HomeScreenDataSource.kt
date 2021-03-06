package com.example.blogapp.data.remote.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction
import com.google.firebase.firestore.SetOptions
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
                post.toObject(Post::class.java)?.let { fbPost ->

                    val isLiked = FirebaseAuth.getInstance().currentUser?.let { safeUser ->
                        isPostLiked(post.id, safeUser.uid)
                    }

                    fbPost.apply {
                        //Obtengo un timeStamp estimado del servidor para que se nos muestre directamente
                        // al publicar el post
                        create_at = post.getTimestamp(
                            "create_at", DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                        )?.toDate()
                        id = post.id

                        if (isLiked != null) {
                            liked = isLiked
                        }
                    }
                    postList.add(fbPost)
                }
            }
        }

        return Result.Success(postList)
    }

    //Esta función compruba si un post tiene o ha sido liked por el usuario
    private suspend fun isPostLiked(postId: String, uid: String): Boolean {
        val post = FirebaseFirestore.getInstance()
            .collection("postsLikes").document(postId).get().await()

        if (!post.exists()) return false

        val likeArray: List<String> = post.get("likes") as List<String>
        return likeArray.contains(uid)
    }

    //Función encargada de la lógica principal de los likes
    fun registerLikeButtonState(postId: String, liked: Boolean) {

        val increment = FieldValue.increment(1)
        val decrement = FieldValue.increment(-1)

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val postRef = FirebaseFirestore.getInstance().collection("posts").document(postId)
        val postsLikesRef = FirebaseFirestore.getInstance().collection("postsLikes").document(postId)

        val database = FirebaseFirestore.getInstance()

        database.runTransaction { transaction ->
            val snapshot = transaction.get(postRef)
            val likeCount = snapshot.getLong("likes")
            if (likeCount != null) {
                if (likeCount >= 0) {
                    if (liked) {
                        if (transaction.get(postsLikesRef).exists()) {
                            transaction.update(postsLikesRef, "likes", FieldValue.arrayUnion(uid))
                        } else {
                            transaction.set(
                                postsLikesRef,
                                hashMapOf("likes" to arrayListOf(uid)),
                                SetOptions.merge()
                            )
                        }

                        transaction.update(postRef, "likes", increment)
                    } else {
                        transaction.update(postRef, "likes", decrement)
                        transaction.update(postsLikesRef, "likes", FieldValue.arrayRemove(uid))
                    }
                }
            }
        }.addOnFailureListener {
            throw Exception(it.message)
        }
    }
}
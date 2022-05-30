package com.example.blogapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogapp.R
import com.example.blogapp.core.BaseViewHolder
import com.example.blogapp.core.TimeUtils
import com.example.blogapp.core.hide
import com.example.blogapp.core.show
import com.example.blogapp.data.model.Post
import com.example.blogapp.databinding.PostItemViewBinding

//Esta clase se encarga de que el recyclerView muestro los datos como le indicamos
class HomeScreenAdapter(
    private val postList: List<Post>,
    onPostClickListener: OnPostClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var postClickListener: OnPostClickListener? = null

    init {
        postClickListener = onPostClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            PostItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeScreenViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is HomeScreenViewHolder -> holder.bind(postList[position])
        }
    }

    override fun getItemCount(): Int = postList.size

    private inner class HomeScreenViewHolder(
        val binding: PostItemViewBinding,
        val context: Context
    ) : BaseViewHolder<Post>(binding.root) {
        override fun bind(item: Post) {

            setupProfileInfo(item)
            addPostTimeStamp(item)
            setupPostImage(item)
            setupPostDescription(item)
            tintHeartIcon(item)
            setupLikeCount(item)
            setLikeClickAction(item)

        }


        //Este método lo utilizamos para crear manejar el perfil del post
        private fun setupProfileInfo(post: Post) {
            Glide.with(context).load(post.poster?.profile_picture).centerCrop()
                .into(binding.profilePicture)
            binding.profileName.text = post.poster?.username
        }

        private fun addPostTimeStamp(post: Post) {
            //Obtenemos el tiempo del servidor y lo transformamos
            val createdAt = (post.create_at?.time?.div(1000L))?.let {
                TimeUtils.getTimeAgo(it.toInt())
            }
            binding.postTimestamp.text = createdAt
        }

        private fun setupPostImage(item: Post) {
            Glide.with(context).load(item.post_image).centerCrop().into(binding.postImage)
        }


        private fun setupPostDescription(item: Post) {//Controlamos que la descripción no esté vacía
            if (item.post_description.isEmpty()) {
                binding.postDescription.hide()
            } else {
                binding.postDescription.text = item.post_description
            }
        }

        //Este método se encargará de pintar el icono del like
        private fun tintHeartIcon(item: Post) {
            if (!item.liked) {
                binding.btnLike.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_round_favorite_border_24
                    )
                )

            } else {
                binding.btnLike.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_round_favorite_24
                    )
                )
            }
        }

        private fun setupLikeCount(item: Post) {
            if (item.likes > 0) {
                binding.likeCount.show()
                "${item.likes} likes".also { binding.likeCount.text = it }
            } else {
                binding.likeCount.hide()
            }
        }

        //Esta función se encarga de, si el like está pintado se despinte y viceversa, luego usamos
        // el postListener para mandar el click a nuestro fragment
        private fun setLikeClickAction(post: Post) {
            binding.btnLike.setOnClickListener {
                if(post.liked) post.apply { liked = false } else post.apply { liked = true }
                tintHeartIcon(post)
                postClickListener?.onLikeButtonClick(post, post.liked)
            }
        }
    }

}

//Esta interfaz nos ayudará a saber si se ha hecho like o no en un post
interface OnPostClickListener {
    fun onLikeButtonClick(post: Post, liked: Boolean)
}

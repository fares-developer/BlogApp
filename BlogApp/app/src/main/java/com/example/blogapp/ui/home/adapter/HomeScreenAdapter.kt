package com.example.blogapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogapp.core.BaseViewHolder
import com.example.blogapp.core.TimeUtils
import com.example.blogapp.core.hide
import com.example.blogapp.data.model.Post
import com.example.blogapp.databinding.PostItemViewBinding

//Esta clase se encarga de que el recyclerView muestro los datos como le indicamos
class HomeScreenAdapter(private val postList: List<Post>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

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

        }

        private fun setupPostDescription(item: Post) {//Controlamos que la descripción no esté vacía
            if (item.post_description.isEmpty()) {
                binding.postDescription.hide()
            } else {
                binding.postDescription.text = item.post_description
            }
        }

        private fun setupPostImage(item: Post) {
            Glide.with(context).load(item.post_image).centerCrop().into(binding.postImage)
        }

        private fun addPostTimeStamp(post: Post) {
            //Obtenemos el tiempo del servidor y lo transformamos
            val createAt = (post.create_at?.time?.div(1000L))?.let {
                TimeUtils.getTimeAgo(it.toInt())
            }
            binding.postTimestamp.text = createAt
        }

        //Este método lo utilizamos para crear manejar el perfil del post
        private fun setupProfileInfo(post: Post) {
            Glide.with(context).load(post.profile_picture).centerCrop().into(binding.profilePicture)
            binding.profileName.text = post.profile_name

        }
    }

}
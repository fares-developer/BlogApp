package com.example.blogapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.blogapp.R
import com.example.blogapp.data.model.Post
import com.example.blogapp.databinding.FragmentHomeScreenBinding
import com.example.blogapp.ui.home.adapter.HomeScreenAdapter
import com.google.firebase.Timestamp

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeScreenBinding.bind(view)

        val postList = listOf(
            Post(
                "https://firebasestorage.googleapis.com/v0/b/blogapp-544be.appspot.com/o/Avatar.png?alt=media&token=df3056e8-4694-4746-9d55-99f8ac7b59bc",
                "Fares Endong", Timestamp.now(),
                "https://firebasestorage.googleapis.com/v0/b/blogapp-544be.appspot.com/o/chandri-anggara-mrP2Y3tERO4-unsplash.jpg?alt=media&token=dc53676d-64db-4db1-8b62-34168ccb4017"
            ),

            Post(
                "https://firebasestorage.googleapis.com/v0/b/blogapp-544be.appspot.com/o/gaston.jpg?alt=media&token=726c85ef-be07-481c-9405-d9618e6b1e9c",
                "Gaston", Timestamp.now(),
                "https://firebasestorage.googleapis.com/v0/b/blogapp-544be.appspot.com/o/sumaid-pal-singh-bakshi-bEjBq9c41RA-unsplash.jpg?alt=media&token=ce4a9b88-ccf0-4f9f-baeb-846beceab898"
            ),

            Post(
                "https://firebasestorage.googleapis.com/v0/b/blogapp-544be.appspot.com/o/jack_profile.png?alt=media&token=2f408489-bcad-4299-a774-5918e22de19a",
                "Jack", Timestamp.now(),
                "https://firebasestorage.googleapis.com/v0/b/blogapp-544be.appspot.com/o/alper-guzeler-inQvRABkTfg-unsplash.jpg?alt=media&token=83dfdee7-8a39-499b-b782-e0c7f255b181"
            ),
        )
        binding.rvHome.adapter = HomeScreenAdapter(postList)
    }

}
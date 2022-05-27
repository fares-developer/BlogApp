package com.example.blogapp.presentation.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.blogapp.core.Result
import kotlinx.coroutines.Dispatchers

class CameraViewModel(private val repo:String):ViewModel() {

    fun uploadPhoto(imageBitmap:Bitmap, description:String) = liveData(Dispatchers.IO){

        emit(Result.Loading())

        try {

        }catch (e:Exception){
            emit(Result.Failure(e))
        }
    }
}
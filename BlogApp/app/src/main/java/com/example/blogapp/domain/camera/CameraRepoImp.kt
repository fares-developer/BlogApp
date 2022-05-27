package com.example.blogapp.domain.camera

import android.graphics.Bitmap
import com.example.blogapp.data.remote.camera.CameraDataSource

class CameraRepoImp(private val dataSource: CameraDataSource) : CameraRepo {

    override suspend fun uploadPhoto(imagBitmap: Bitmap, description: String) {
        dataSource.uploadPhoto(imagBitmap, description)
    }
}
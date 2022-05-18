package com.example.navegacion

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

class MainViewModel:ViewModel() {

    //Creamos un liveData de User para poder pasar datos al segundo fragment
    private var user = MutableLiveData<User>()
    val _user:LiveData<User> get() = user

    fun setUser(_user:User){
        user.value = _user
    }
}

//Para que aparezca esta anotacion debe ir a gradle app y añadir id 'kotlin-android-extensions' en plugins
@Parcelize
data class User(val name:String, val age:Int):Parcelable
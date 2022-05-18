package com.example.navegacion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    //Creamos un liveData de User para poder pasar datos al segundo fragment
    private var user = MutableLiveData<User>()
    val _user:LiveData<User> get() = user

    fun setUser(_user:User){
        user.value = _user
    }
}

data class User(val name:String, val age:Int)
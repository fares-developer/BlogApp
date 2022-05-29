package com.example.blogapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.blogapp.core.Result
import com.example.blogapp.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import java.lang.Exception

//Esta clase es la que va a conectar la info del repositorio con la UI
class HomeScreenViewModel(private val repo: HomeScreenRepo): ViewModel() {

    fun fetchLatestPost() = liveData(Dispatchers.IO){
        emit(Result.Loading())

        //Esta función nos devuelve dos valores onSuccess y unFailure
        kotlin.runCatching {
            repo.getLatestPosts()
        }.onSuccess {flowlist -> //Esto devuelve un flujo
            //Esta función obtiene los datos del flujo
            flowlist.collect {
                emit(it)
            }
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }
}

//Con esta clase indicamos cómo se debe crear el viewModel
class HomeScreenViewModelFactory(private val repo: HomeScreenRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }
}
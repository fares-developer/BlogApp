package com.example.blogapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.blogapp.core.Result
import com.example.blogapp.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers

//Esta clase es la que va a conectar la info del repositorio con la UI
class HomeScreenViewModel(private val repo: HomeScreenRepo) : ViewModel() {

    fun fetchLatestPost() = liveData(Dispatchers.IO) {
        emit(Result.Loading())

        kotlin.runCatching {
            repo.getLatestPosts()
        }.onSuccess { postList ->
            emit(postList)
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }

    fun registerLikeButtonState(postId: String, liked: Boolean) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO){

            emit(Result.Loading())

            kotlin.runCatching {
                repo.registerLikeButtonState(postId,liked)
            }.onSuccess {
                emit(Result.Success(Unit))
            }.onFailure {
                emit(Result.Failure(Exception(it.message)))
            }

    }
}

//Con esta clase indicamos cómo se debe crear el viewModel
class HomeScreenViewModelFactory(private val repo: HomeScreenRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }
}
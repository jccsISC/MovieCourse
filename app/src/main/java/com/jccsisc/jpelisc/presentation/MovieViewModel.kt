package com.jccsisc.jpelisc.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.jccsisc.jpelisc.core.MyResource
import com.jccsisc.jpelisc.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository): ViewModel() {

    //lleva el hilo donde se va a ejecutar esa corrutina con Dispatchers.IO
    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        //necesitamos decirle a la vista que tenemos 3 estados, cargar, estado de exito y el fallo
        emit(MyResource.Loading())

        try {
            emit(MyResource.Success(Triple(repo.getUpcomingMovies(), repo.getPopularMovies(), repo.getTopRatedMovies())))
        }catch (e: Exception) {
            emit(MyResource.Failure(e)) //estado de error
        }
    }

}

//para poder mandar 5 peticiones
data class NTuples5<T1, T2, T3, T4, T5>(val t1: T1, val t2: T2, val t3: T3, val t4: T4, val t5: T5)

class MovieViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}
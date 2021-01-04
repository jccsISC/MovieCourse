package com.jccsisc.jpelisc.data.remote

import com.jccsisc.jpelisc.aplication.AppConstTants
import com.jccsisc.jpelisc.data.model.MovieList
import com.jccsisc.jpelisc.repository.WebService

class RemoteMovieDataSource(private val webService: WebService) {

    suspend fun getUpComingMovies(): MovieList = webService.getUpcomingMovies(AppConstTants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstTants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstTants.API_KEY)
}
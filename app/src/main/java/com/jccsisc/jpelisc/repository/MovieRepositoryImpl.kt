package com.jccsisc.jpelisc.repository

import com.jccsisc.jpelisc.data.model.MovieList
import com.jccsisc.jpelisc.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDataSource): MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList = dataSourceRemote.getUpComingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSourceRemote.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSourceRemote.getPopularMovies()
}
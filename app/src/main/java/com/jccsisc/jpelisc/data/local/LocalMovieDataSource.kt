package com.jccsisc.jpelisc.data.local

import com.jccsisc.jpelisc.data.model.MovieEntity
import com.jccsisc.jpelisc.data.model.MovieList
import com.jccsisc.jpelisc.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpComingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming"}.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }
}
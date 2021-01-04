package com.jccsisc.jpelisc.repository

import com.jccsisc.jpelisc.data.model.MovieList

interface MovieRepository {
   suspend fun getUpcomingMovies(): MovieList
   suspend fun getTopRatedMovies(): MovieList
   suspend fun getPopularMovies(): MovieList
}
package com.jccsisc.jpelisc.repository

import com.jccsisc.jpelisc.data.local.LocalMovieDataSource
import com.jccsisc.jpelisc.data.model.MovieList
import com.jccsisc.jpelisc.data.model.toMovieEntity
import com.jccsisc.jpelisc.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDataSource,
                          private var dataSourceLocal: LocalMovieDataSource): MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        dataSourceRemote.getUpComingMovies().results.forEach {movie ->
            //guardando en base de datos local ahora lo transformamos a entity
            dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
        }
        return dataSourceLocal.getUpComingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList  {
        dataSourceRemote.getTopRatedMovies().results.forEach {movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
        }
        return dataSourceLocal.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): MovieList {
        dataSourceRemote.getPopularMovies().results.forEach {movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
        }
        return dataSourceLocal.getPopularMovies()
    }
}
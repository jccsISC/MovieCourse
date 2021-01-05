package com.jccsisc.jpelisc.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jccsisc.jpelisc.data.model.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //si ya existe el elemento lo reemplaza
    suspend fun saveMovie(movie: MovieEntity)

}
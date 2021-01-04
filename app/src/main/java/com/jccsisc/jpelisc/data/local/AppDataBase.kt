package com.jccsisc.jpelisc.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jccsisc.jpelisc.data.model.MovieEntity

//la ahcemos abstracta para poder acceder a los metodos abstractos
@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    //creando instancia de room
    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {

            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, "movie_table"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
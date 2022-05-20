package com.example.firstapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstapp.data.model.MovieEntity


@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDato(): MovieDao //Esta función es para poder acceder y guardar el movie

    //Creamos una única instancia de la base de datos
    companion object {
        private var INSTANCE: AppDatabase? = null

        //Esta función es para obtener la instancia de la base de datos si existe , si no existe la creamos
        fun getDatabase(context: Context): AppDatabase {
            INSTANCE?.let {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movie_table"
                ).build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
package com.example.firstapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firstapp.data.model.MovieEntity

@Dao
interface MovieDao {

    @Query("select * from movieentity")
    suspend fun getAllMovies():List<MovieEntity>

    //Para evitar que cuando insertamos datos se dupliquen utilizamos el onConflict para remplazar
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie:MovieEntity)
}
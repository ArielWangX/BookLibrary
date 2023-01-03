package com.raywenderlich.android.BookLibrary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.BookLibrary.model.Genre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    fun getGenre(): List<Genre>

    @Query("SELECT * FROM genre WHERE id = :genreId")
    fun getGenreById(genreId: String): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGenres(genres: List<Genre>)
}
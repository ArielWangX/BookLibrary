package com.raywenderlich.android.BookLibrary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.raywenderlich.android.BookLibrary.model.Genre
import com.raywenderlich.android.BookLibrary.model.relations.BooksByGenre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    suspend fun getGenre(): List<Genre>

    @Query("SELECT * FROM genre WHERE id = :genreId")
    suspend fun getGenreById(genreId: String): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenres(genres: List<Genre>)

    @Transaction
    @Query("SELECT * FROM genre WHERE id = :genreId")
    suspend fun getBooksByGenre(genreId: String): BooksByGenre

    @Transaction
    @Query("SELECT * FROM genre")
    suspend fun getBooksByGenres(): List<BooksByGenre>
}
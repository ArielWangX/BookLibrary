package com.raywenderlich.android.BookLibrary.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.BookLibrary.model.Book
import com.raywenderlich.android.BookLibrary.model.relations.BookAndGenre

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getBooks(): List<BookAndGenre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book)

    @Delete
    fun removeBook(book: Book)

    @Query("SELECT * FROM books WHERE id = :bookId")
    fun getBookById(bookId: String): Book
}
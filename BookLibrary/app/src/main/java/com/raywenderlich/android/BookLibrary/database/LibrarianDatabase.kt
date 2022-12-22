package com.raywenderlich.android.BookLibrary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raywenderlich.android.BookLibrary.database.dao.BookDao
import com.raywenderlich.android.BookLibrary.database.dao.GenreDao
import com.raywenderlich.android.BookLibrary.database.dao.ReadingListDao
import com.raywenderlich.android.BookLibrary.database.dao.ReviewDao
import com.raywenderlich.android.BookLibrary.model.Book
import com.raywenderlich.android.BookLibrary.model.Genre

const val DATABASE_VERSION = 1

@Database(
    entities = [Book::class, Genre::class],
    version = DATABASE_VERSION
)
abstract class LibrarianDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "Librarian"

        fun buildDatabase(context: Context): LibrarianDatabase {
            return Room.databaseBuilder(
                context,
                LibrarianDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun bookDao(): BookDao

    abstract fun genreDao(): GenreDao

    abstract fun readingListDao(): ReadingListDao

    abstract fun reviewDao(): ReviewDao
}
package com.raywenderlich.android.BookLibrary.repository

import com.raywenderlich.android.BookLibrary.database.dao.BookDao
import com.raywenderlich.android.BookLibrary.database.dao.GenreDao
import com.raywenderlich.android.BookLibrary.database.dao.ReadingListDao
import com.raywenderlich.android.BookLibrary.database.dao.ReviewDao

class LibrarianRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : LibrarianRepository {
}
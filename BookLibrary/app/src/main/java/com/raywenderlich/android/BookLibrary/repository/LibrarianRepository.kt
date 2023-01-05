package com.raywenderlich.android.BookLibrary.repository

import com.raywenderlich.android.BookLibrary.model.Book
import com.raywenderlich.android.BookLibrary.model.Genre
import com.raywenderlich.android.BookLibrary.model.ReadingList
import com.raywenderlich.android.BookLibrary.model.Review
import com.raywenderlich.android.BookLibrary.model.relations.BookAndGenre
import com.raywenderlich.android.BookLibrary.model.relations.BookReview
import com.raywenderlich.android.BookLibrary.model.relations.BooksByGenre
import com.raywenderlich.android.BookLibrary.model.relations.ReadingListsWithBooks
import kotlinx.coroutines.flow.Flow

interface LibrarianRepository {

    suspend fun addBook(book: Book)

    suspend fun getBooks(): List<BookAndGenre>

    suspend fun removeBook(book: Book)

    suspend fun getBookById(bookId: String): Book

    suspend fun getGenres(): List<Genre>

    suspend fun getGenreById(genreId: String): Genre

    suspend fun addGenres(genres: List<Genre>)

    suspend fun addReview(review: Review)

    suspend fun updateReview(review: Review)

    suspend fun getReviews(): List<BookReview>

    fun getReviewsFlow(): Flow<List<BookReview>>

    suspend fun removeReview(review: Review)

    suspend fun getReviewById(reviewId: String): BookReview

    suspend fun addReadingList(readingList: ReadingList)

    suspend fun getReadingLists(): List<ReadingListsWithBooks>

    fun getReadingListsFlow(): Flow<List<ReadingListsWithBooks>>

    suspend fun removeReadingList(readingList: ReadingList)

    suspend fun getBooksByGenre(genreId: String): List<BookAndGenre>

    suspend fun getBooksByRating(rating: Int): List<BookAndGenre>
}
package com.raywenderlich.android.BookLibrary.repository

import com.raywenderlich.android.BookLibrary.model.Book
import com.raywenderlich.android.BookLibrary.model.Genre
import com.raywenderlich.android.BookLibrary.model.ReadingList
import com.raywenderlich.android.BookLibrary.model.Review
import com.raywenderlich.android.BookLibrary.model.relations.BookAndGenre
import com.raywenderlich.android.BookLibrary.model.relations.BookReview
import com.raywenderlich.android.BookLibrary.model.relations.BooksByGenre
import com.raywenderlich.android.BookLibrary.model.relations.ReadingListsWithBooks

interface LibrarianRepository {

    fun addBook(book: Book)

    fun getBooks(): List<BookAndGenre>

    fun removeBook(book: Book)

    fun getBookById(bookId: String): Book

    fun getGenres(): List<Genre>

    fun getGenreById(genreId: String): Genre

    fun addGenres(genres: List<Genre>)

    fun addReview(review: Review)

    fun updateReview(review: Review)

    fun getReviews(): List<BookReview>

    fun removeReview(review: Review)

    fun getReviewById(reviewId: String): BookReview

    fun addReadingList(readingList: ReadingList)

    fun getReadingLists(): List<ReadingListsWithBooks>

    fun removeReadingList(readingList: ReadingList)

    fun getBooksByGenre(genreId: String): List<BookAndGenre>

    fun getBooksByRating(rating: Int): List<BookAndGenre>
}
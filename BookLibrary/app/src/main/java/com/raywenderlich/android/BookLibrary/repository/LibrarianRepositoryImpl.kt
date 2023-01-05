package com.raywenderlich.android.BookLibrary.repository

import com.raywenderlich.android.BookLibrary.database.dao.BookDao
import com.raywenderlich.android.BookLibrary.database.dao.GenreDao
import com.raywenderlich.android.BookLibrary.database.dao.ReadingListDao
import com.raywenderlich.android.BookLibrary.database.dao.ReviewDao
import com.raywenderlich.android.BookLibrary.model.Book
import com.raywenderlich.android.BookLibrary.model.Genre
import com.raywenderlich.android.BookLibrary.model.ReadingList
import com.raywenderlich.android.BookLibrary.model.Review
import com.raywenderlich.android.BookLibrary.model.relations.BookAndGenre
import com.raywenderlich.android.BookLibrary.model.relations.BookReview
import com.raywenderlich.android.BookLibrary.model.relations.BooksByGenre
import com.raywenderlich.android.BookLibrary.model.relations.ReadingListsWithBooks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LibrarianRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : LibrarianRepository {

    override suspend fun addBook(book: Book) = bookDao.addBook(book)

    override suspend fun getBooks(): List<BookAndGenre>  = bookDao.getBooks()

    override suspend fun removeBook(book: Book) = bookDao.removeBook(book)

    override suspend fun getBookById(bookId: String): Book = bookDao.getBookById(bookId)

    override suspend fun getGenres(): List<Genre> = genreDao.getGenre()

    override suspend fun getGenreById(genreId: String): Genre = genreDao.getGenreById(genreId)

    override suspend fun addGenres(genres: List<Genre>) = genreDao.addGenres(genres)

    override suspend fun addReview(review: Review) = reviewDao.addReview(review)

    override suspend fun updateReview(review: Review) = reviewDao.updateReview(review)

    override suspend fun getReviews(): List<BookReview> = reviewDao.getReviews()

    override fun getReviewsFlow(): Flow<List<BookReview>> = reviewDao.getReviewsFlow()

    override suspend fun removeReview(review: Review) = reviewDao.removeReview(review)

    override suspend fun getReviewById(reviewId: String): BookReview = reviewDao.getReviewById(reviewId)

    override suspend fun addReadingList(readingList: ReadingList) = readingListDao.addReadingList(readingList)

    override suspend fun getReadingLists(): List<ReadingListsWithBooks> =
        readingListDao.getReadingLists().map { readingList ->
            ReadingListsWithBooks( readingList.id, readingList.name, emptyList() )
    }

    override fun getReadingListsFlow(): Flow<List<ReadingListsWithBooks>> =
        readingListDao.getReadingListsFlow().map { item ->
            item.map {
                ReadingListsWithBooks(it.id, it.name, emptyList())
            }
        }

    override suspend fun removeReadingList(readingList: ReadingList) = readingListDao.removeReadingList(readingList)

    override suspend fun getBooksByGenre(genreId: String): List<BookAndGenre> =
        genreDao.getBooksByGenre(genreId).let { booksByGenre ->
            val books = booksByGenre.books ?: return emptyList()

            return books.map { BookAndGenre(it, booksByGenre.genre) }
        }

    override suspend fun getBooksByRating(rating: Int): List<BookAndGenre> {
        val reviewsByRating = reviewDao.getBookReviewByRating(rating)

        return reviewsByRating.map { BookAndGenre(it.book, genreDao.getGenreById(it.book.genreId)) }
    }


}
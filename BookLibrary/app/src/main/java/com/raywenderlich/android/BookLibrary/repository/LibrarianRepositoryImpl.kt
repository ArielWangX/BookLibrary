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


class LibrarianRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : LibrarianRepository {

    override fun addBook(book: Book) = bookDao.addBook(book)

    override fun getBooks(): List<BookAndGenre>  = bookDao.getBooks()

    override fun removeBook(book: Book) = bookDao.removeBook(book)

    override fun getBookById(bookId: String): Book = bookDao.getBookById(bookId)

    override fun getGenres(): List<Genre> = genreDao.getGenre()

    override fun getGenreById(genreId: String): Genre = genreDao.getGenreById(genreId)

    override fun addGenres(genres: List<Genre>) = genreDao.addGenres(genres)

    override fun addReview(review: Review) = reviewDao.addReview(review)

    override fun updateReview(review: Review) = reviewDao.updateReview(review)

    override fun getReviews(): List<BookReview> = reviewDao.getReviews()

    override fun removeReview(review: Review) = reviewDao.removeReview(review)

    override fun getReviewById(reviewId: String): BookReview = reviewDao.getReviewById(reviewId)

    override fun addReadingList(readingList: ReadingList) = readingListDao.addReadingList(readingList)

    override fun getReadingLists(): List<ReadingListsWithBooks> =
        readingListDao.getReadingLists().map { readingList ->
            ReadingListsWithBooks( readingList.id, readingList.name, bookDao.getBooks() )
    }

    override fun removeReadingList(readingList: ReadingList) = readingListDao.removeReadingList(readingList)

    override fun getBooksByGenre(genreId: String): List<BookAndGenre> =
        genreDao.getBooksByGenre(genreId).let { booksByGenre ->
            val books = booksByGenre.books ?: return emptyList()

            return books.map { BookAndGenre(it, booksByGenre.genre) }
        }

    override fun getBooksByRating(rating: Int): List<BookAndGenre> {
        val reviewsByRating = reviewDao.getBookReviewByRating(rating)

        return reviewsByRating.map { BookAndGenre(it.book, genreDao.getGenreById(it.book.genreId)) }
    }


}
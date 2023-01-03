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
import com.raywenderlich.android.BookLibrary.model.relations.ReadingListsWithBooks


class LibrarianRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : LibrarianRepository {

    override fun addBook(book: Book) = bookDao.addBook(book)

    override fun getBooks(): List<BookAndGenre>  = bookDao.getBooks().map {
        BookAndGenre(it, genreDao.getGenreById(it.genreId))
    }

    override fun removeBook(book: Book) = bookDao.removeBook(book)

    override fun getBookById(bookId: String): Book = bookDao.getBookById(bookId)

    override fun getGenres(): List<Genre> = genreDao.getGenre()

    override fun getGenreById(genreId: String): Genre = genreDao.getGenreById(genreId)

    override fun addGenres(genres: List<Genre>) = genreDao.addGenres(genres)

    override fun addReview(review: Review) = reviewDao.addReview(review)

    override fun updateReview(review: Review) = reviewDao.updateReview(review)

    override fun getReviews(): List<BookReview> = reviewDao.getReviews().map {
        BookReview(it, bookDao.getBookById(it.bookId))
    }

    override fun removeReview(review: Review) = reviewDao.removeReview(review)

    override fun getReviewById(reviewId: String): BookReview {
        val review = reviewDao.getReviewById(reviewId)

        return BookReview(review, bookDao.getBookById(review.bookId))
    }

    override fun addReadingList(readingList: ReadingList) = readingListDao.addReadingList(readingList)

    override fun getReadingLists(): List<ReadingListsWithBooks> = readingListDao.getReadingLists().map { readingList ->
        ReadingListsWithBooks(
            readingList.id, readingList.name, bookDao.getBooks().map {
                BookAndGenre(it, genreDao.getGenreById(it.genreId))
            }
        )
    }

    override fun removeReadingList(readingList: ReadingList) = readingListDao.removeReadingList(readingList)
}
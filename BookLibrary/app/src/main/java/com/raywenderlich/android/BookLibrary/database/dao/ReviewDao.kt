package com.raywenderlich.android.BookLibrary.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.raywenderlich.android.BookLibrary.model.Review
import com.raywenderlich.android.BookLibrary.model.relations.BookReview

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReview(review: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateReview(review: Review)

    @Query("SELECT * FROM Review")
    fun getReviews(): List<BookReview>

    @Query("SELECT * FROM Review WHERE id = :reviewId")
    fun getReviewById(reviewId: String): BookReview

    @Delete
    fun removeReview(review: Review)

    @Transaction
    @Query("SELECT * FROM Review WHERE rating >= :rating")
    fun getBookReviewByRating(rating: Int): List<BookReview>
}
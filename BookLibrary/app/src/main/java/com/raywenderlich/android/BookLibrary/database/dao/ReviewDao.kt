package com.raywenderlich.android.BookLibrary.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.raywenderlich.android.BookLibrary.model.Review
import com.raywenderlich.android.BookLibrary.model.relations.BookReview
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReview(review: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateReview(review: Review)

    @Query("SELECT * FROM Review")
    suspend fun getReviews(): List<BookReview>

    @Transaction
    @Query("SELECT * FROM Review")
    fun getReviewsFlow(): Flow<List<BookReview>>

    @Query("SELECT * FROM Review WHERE id = :reviewId")
    suspend fun getReviewById(reviewId: String): BookReview

    @Delete
    suspend fun removeReview(review: Review)

    @Transaction
    @Query("SELECT * FROM Review WHERE rating >= :rating")
    suspend fun getBookReviewByRating(rating: Int): List<BookReview>
}
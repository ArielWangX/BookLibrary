package com.raywenderlich.android.BookLibrary.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.BookLibrary.model.Review

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReview(review: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateReview(review: Review)

    @Query("SELECT * FROM Review")
    fun getReviews(): List<Review>

    @Query("SELECT * FROM Review WHERE id = :reviewId")
    fun getReviewById(reviewId: String): Review

    @Delete
    fun removeReview(review: Review)
}
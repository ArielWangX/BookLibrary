package com.raywenderlich.android.BookLibrary.database.dao

import androidx.annotation.RequiresPermission.Read
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.raywenderlich.android.BookLibrary.model.ReadingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReadingList(readingList: ReadingList)

    @Query("SELECT * FROM ReadingList")
    suspend fun getReadingLists(): List<ReadingList>

    @Transaction
    @Query("SELECT * FROM ReadingList")
    fun getReadingListsFlow(): Flow<List<ReadingList>>

    @Delete
    suspend fun removeReadingList(readingList: ReadingList)

    @Query("SELECT * FROM readinglist WHERE id = :listId")
    suspend fun getReadingListById(listId: String): ReadingList

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateReadingList(newReadingList: ReadingList)
}
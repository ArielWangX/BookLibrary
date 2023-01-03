package com.raywenderlich.android.BookLibrary.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.BookLibrary.model.ReadingList

@Dao
interface ReadingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReadingList(readingList: ReadingList)

    @Query("SELECT * FROM ReadingList")
    fun getReadingLists(): List<ReadingList>

    @Delete
    fun removeReadingList(readingList: ReadingList)
}
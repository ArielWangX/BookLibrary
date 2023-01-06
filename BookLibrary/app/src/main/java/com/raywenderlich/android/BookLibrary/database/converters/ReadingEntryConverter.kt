package com.raywenderlich.android.BookLibrary.database.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.raywenderlich.android.BookLibrary.App
import com.raywenderlich.android.BookLibrary.model.ReadingEntry

class ReadingEntryConverter {

    @TypeConverter
    fun fromEntries(list: List<ReadingEntry>): String = App.gson.toJson(list)

    @TypeConverter
    fun toEntries(json: String): List<ReadingEntry> {
        val listType = object : TypeToken<List<ReadingEntry>>() {}.type

        return try {
            App.gson.fromJson(json, listType)
        }catch (error: Throwable) {
            emptyList()
        }
    }
}
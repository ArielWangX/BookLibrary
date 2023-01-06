package com.raywenderlich.android.BookLibrary.database.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.raywenderlich.android.BookLibrary.App

class BookIdsConverter {

    @TypeConverter
    fun fromBookIds(list: List<String>): String = App.gson.toJson(list)

    @TypeConverter
    fun toBookIds(json: String): List<String> {
        val typeToken = object : TypeToken<List<String>>() {}.type

        return try {
            App.gson.fromJson(json, typeToken)
        } catch (error: Throwable) {
            emptyList()
        }
    }
}
package com.prilepskiy.shopbookapp.data.database.books

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.prilepskiy.shopbookapp.data.response.AuthorResponse
import com.prilepskiy.shopbookapp.data.response.FormatsResponse
import com.prilepskiy.shopbookapp.data.response.TranslatorResponse

class BookEntityConverter {

    @TypeConverter
    fun authorListToJson(value: List<AuthorResponse>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToAuthorList(value: String) = Gson().fromJson(value, Array<AuthorResponse>::class.java).toList()

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()


    @TypeConverter
    fun translatorListToJson(value: List<TranslatorResponse>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToTranslatorList(value: String) = Gson().fromJson(value, Array<TranslatorResponse>::class.java).toList()


    @TypeConverter
    fun formatsToJson(value: FormatsResponse) = Gson().toJson(value)

    @TypeConverter
    fun jsonToformats(value: String) = Gson().fromJson(value, FormatsResponse::class.java)

}
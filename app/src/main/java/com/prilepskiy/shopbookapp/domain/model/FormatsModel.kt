package com.prilepskiy.shopbookapp.domain.model

import com.google.gson.annotations.SerializedName
import com.prilepskiy.shopbookapp.data.response.FormatsResponse
import com.prilepskiy.shopbookapp.data.response.TranslatorResponse

data class FormatsModel (
val ebook:String,
val image_jpeg:String,
val rdf_xml:String
){
    companion object{

    fun from(data: FormatsResponse?): FormatsModel {
        return  FormatsModel(data?.ebook?:"",data?.image_jpeg?:"",data?.rdf_xml?:"")
    }
}
}
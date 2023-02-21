package com.prilepskiy.shopbookapp.data.response

import com.prilepskiy.shopbookapp.domain.model.AuthorModel
import com.prilepskiy.shopbookapp.domain.model.TranslatorModel

data class TranslatorResponse(
    val birth_year: Int?,
    val death_year: Int?,
    val name: String?
){
    companion object{
        fun from(data: TranslatorModel):TranslatorResponse  = with(data){
            TranslatorResponse(birth_year, death_year, name)
        }
        fun from(data:List<TranslatorModel>):List<TranslatorResponse> {
            val temp:MutableList<TranslatorResponse> = mutableListOf()
            data.forEach {
                temp.add(from(it))
            }
            return temp
        }
    }

}
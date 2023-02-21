package com.prilepskiy.shopbookapp.domain.model

import com.prilepskiy.shopbookapp.data.response.TranslatorResponse

data class TranslatorModel(
    val birth_year: Int,
    val death_year: Int,
    val name: String
){
    companion object{
        fun from(data: TranslatorResponse): TranslatorModel = with(data){
            TranslatorModel(birth_year, death_year, name)
        }
        fun from(data:List<TranslatorResponse>):List<TranslatorModel> {
            val temp:MutableList<TranslatorModel> = mutableListOf()
            data.forEach {
                temp.add(from(it))
            }
            return temp
        }
    }

}
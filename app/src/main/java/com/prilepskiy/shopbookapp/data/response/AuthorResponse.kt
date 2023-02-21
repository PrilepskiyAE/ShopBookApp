package com.prilepskiy.shopbookapp.data.response

import com.prilepskiy.shopbookapp.domain.model.AuthorModel

data class AuthorResponse(
    val birth_year: Int?,
    val death_year: Int?,
    val name: String?
){
    companion object{
        fun from(data:AuthorModel):AuthorResponse  = with(data){
            AuthorResponse(birth_year, death_year, name)
        }
        fun from(data:List<AuthorModel>):List<AuthorResponse> {
            val temp:MutableList<AuthorResponse> = mutableListOf()
            data.forEach {
                temp.add(from(it))
            }
            return temp
        }
    }

}
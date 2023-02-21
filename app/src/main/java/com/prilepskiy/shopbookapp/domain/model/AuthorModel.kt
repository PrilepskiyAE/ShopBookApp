package com.prilepskiy.shopbookapp.domain.model

import com.prilepskiy.shopbookapp.data.response.AuthorResponse

data class AuthorModel(
    val birth_year: Int,
    val death_year: Int,
    val name: String
) {
    companion object {

        fun from(data: AuthorResponse?): AuthorModel  {
          return  AuthorModel(data?.birth_year?:0, data?.death_year?:0, data?.name?:"")
        }

        fun from(data2: List<AuthorResponse?>?): List<AuthorModel> {
            val temp: MutableList<AuthorModel> = mutableListOf()
            data2?.forEach {
                temp.add(from(it))
            }
            return temp
        }
    }
}
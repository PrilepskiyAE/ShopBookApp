package com.prilepskiy.shopbookapp.domain.model

import com.prilepskiy.shopbookapp.data.response.AuthorResponse

data class AuthorModel(
    val birth_year: Int,
    val death_year: Int,
    val name: String
) {
    companion object {

        fun from(data: AuthorResponse): AuthorModel = with(data) {
            AuthorModel(birth_year, death_year, name)
        }

        fun from(data: List<AuthorResponse>): List<AuthorModel> {
            val temp: MutableList<AuthorModel> = mutableListOf()
            data.forEach {
                temp.add(from(it))
            }
            return temp
        }
    }
}
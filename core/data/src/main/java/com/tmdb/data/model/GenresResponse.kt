package com.tmdb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
    @SerialName("genres")
    val genres: List<GenreResponse>
)

@Serializable
data class GenreResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)
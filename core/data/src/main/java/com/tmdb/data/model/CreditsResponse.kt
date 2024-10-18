package com.tmdb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("cast")
    val cast: List<CastResponse>,
    @SerialName("id")
    val id: Int
)

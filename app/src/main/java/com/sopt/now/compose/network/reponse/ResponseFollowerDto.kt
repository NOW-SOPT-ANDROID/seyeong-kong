package com.sopt.now.compose.network.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFollowerDto(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val `data`: List<Data>,
    val support: Support,
) {
    @Serializable
    data class Data(
        val id: Int,
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        @SerialName("last_name")
        val lastName: String,
        val avatar: String,
    )

    @Serializable
    data class Support(
        val url: String,
        val text: String,
    )
}
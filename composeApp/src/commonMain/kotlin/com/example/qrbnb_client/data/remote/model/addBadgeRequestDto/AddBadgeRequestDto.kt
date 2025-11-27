package com.example.qrbnb_client.data.remote.model.addBadgeRequestDto

import kotlinx.serialization.Serializable

@Serializable
data class AddBadgeRequestDto(
    val name: String,
    val color: String,
    val description: String?,

)
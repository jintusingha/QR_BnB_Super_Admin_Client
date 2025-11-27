package com.example.qrbnb_client.data.remote.model.addBadgeRequestDto

import kotlinx.serialization.Serializable

@Serializable
data class AddBadgeResponseDto(
    val success: Boolean,
    val data: BadgeData?
)
@Serializable
data class BadgeData(
    val id: String,
    val name: String,
    val color: String,
    val description: String?
)
package com.example.qrbnb_client.data.remote.model.seatingListResponseDto

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class SeatingListResponseDto(
    val success: Boolean,
    val data: List<SeatingItemDto>
)

@Serializable
data class SeatingItemDto(
    val id: String,
    val name: String,
    val type: String,
    val qrCodeUrl: String,
    val imageUrl: String,
    val createdAt: String
)
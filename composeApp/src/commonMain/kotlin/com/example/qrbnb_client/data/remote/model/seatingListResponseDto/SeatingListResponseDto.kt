package com.example.qrbnb_client.data.remote.model.seatingListResponseDto

import kotlinx.serialization.Serializable

@Serializable
data class SeatingListResponseDto(
    val success: Boolean,
    val data: List<SeatingItemDto>,
)

@Serializable
data class SeatingItemDto(
    val id: String,
    val type: String,
    val name: String,
    val description: String,
    val capacity: Int,
    val isActive: Boolean,
    val imageUrl: String,
    val qrCodeUrl: String,
)

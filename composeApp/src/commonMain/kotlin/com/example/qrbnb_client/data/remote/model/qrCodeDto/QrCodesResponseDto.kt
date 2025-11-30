package com.example.qrbnb_client.data.remote.model.qrCodeDto

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class QrCodesResponseDto(
    val success: Boolean,
    val data: List<QrCodeItemDto>
)

@Serializable
data class QrCodeItemDto(
    val id: String,
    val name: String,
    val type: String,
    val qrCodeUrl: String,
    val imageUrl: String,
    val createdAt: String
)
package com.example.qrbnb_client.data.remote.model.generateQrResponseDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateQrResponseDto(
    val success: Boolean,
     val message: String,
    val data: GenerateQrDataDto
)

@Serializable
data class GenerateQrDataDto(
     val qrUrl: String,
    val deepLink: String
)
package com.example.qrbnb_client.data.remote.model.veriftyOtpDto

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class VerifyOtpResponseDto(
    val success: Boolean,
    val message: String,
    val data: VerifyOtpDataDto
)

@Serializable
data class VerifyOtpDataDto(
    val userId: String,
    val clientId: String,
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int
)
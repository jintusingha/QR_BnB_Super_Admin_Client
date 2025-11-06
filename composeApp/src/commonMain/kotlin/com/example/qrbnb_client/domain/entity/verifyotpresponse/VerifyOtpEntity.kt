package com.example.qrbnb_client.domain.entity.verifyotpresponse

data class VerifyOtpEntity(
    val success: Boolean,
    val message: String,
    val data: VerifyOtpDataEntity
)

data class VerifyOtpDataEntity(
    val userId: String,
    val clientId: String,
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int
)
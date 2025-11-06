package com.example.qrbnb_client.data.remote.model.verifyotpRequestDto

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequestDto(
    val phoneNumber: String,
    val otp: String
)
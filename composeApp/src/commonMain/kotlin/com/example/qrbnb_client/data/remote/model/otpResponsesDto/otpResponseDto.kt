package com.example.qrbnb_client.data.remote.model.otpResponsesDto

import kotlinx.serialization.Serializable

@Serializable
data class OtpResponseDto(
    val success:Boolean,
    val message:String,
    val data:OtpDataDto
)
@Serializable
data class OtpDataDto(
    val otpExpiresIn:Int
)
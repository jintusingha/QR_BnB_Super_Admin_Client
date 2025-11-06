package com.example.qrbnb_client.domain.entity.otpResponse


data class OtpResponse(
    val isSuccess:Boolean,
    val message:String,
    val otpExpiresIn:Int
)
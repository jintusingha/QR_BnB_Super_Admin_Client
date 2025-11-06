package com.example.qrbnb_client.domain.repository


import com.example.qrbnb_client.domain.entity.otpResponse.OtpResponse


interface OtpRepository{
    suspend fun sendOtp(phoneNumber:String): OtpResponse
}
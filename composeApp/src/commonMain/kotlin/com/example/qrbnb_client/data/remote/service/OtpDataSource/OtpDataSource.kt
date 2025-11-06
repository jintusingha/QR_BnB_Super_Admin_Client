package com.example.qrbnb_client.data.remote.service.OtpDataSource

import com.example.qrbnb_client.data.remote.model.otpResponsesDto.OtpResponseDto

interface OtpDataSource{
    suspend fun sendOtp(phoneNumber:String): OtpResponseDto
}
package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.verifyotpresponse.VerifyOtpEntity

interface VerifyOtpRepository{
    suspend fun verifyOtp(phoneNumber:String,otp:String): VerifyOtpEntity
}
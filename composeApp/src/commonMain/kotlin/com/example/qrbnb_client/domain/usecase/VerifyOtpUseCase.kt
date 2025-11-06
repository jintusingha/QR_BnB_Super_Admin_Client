package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.verifyotpresponse.VerifyOtpEntity
import com.example.qrbnb_client.domain.repository.VerifyOtpRepository

class VerifyOtpUseCase (private val repository: VerifyOtpRepository){
    suspend operator fun invoke(phoneNumber:String,otp:String): VerifyOtpEntity{
        return repository.verifyOtp(phoneNumber,otp)
    }
}
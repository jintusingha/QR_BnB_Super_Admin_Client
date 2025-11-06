package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.otpResponse.OtpResponse
import com.example.qrbnb_client.domain.repository.OtpRepository

class SendOtpUseCase (private val otpRepository: OtpRepository){
    suspend operator fun invoke(phoneNumber:String): OtpResponse {
        return otpRepository.sendOtp(phoneNumber)
    }
}
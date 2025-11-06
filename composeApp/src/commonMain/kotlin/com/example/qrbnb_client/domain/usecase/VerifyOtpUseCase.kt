package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.data.TokenStorage
import com.example.qrbnb_client.domain.entity.verifyotpresponse.VerifyOtpEntity
import com.example.qrbnb_client.domain.repository.VerifyOtpRepository

class VerifyOtpUseCase(
    private val repository: VerifyOtpRepository,
    private val tokenStorage: TokenStorage,
) {
    suspend operator fun invoke(
        phoneNumber: String,
        otp: String,
    ): VerifyOtpEntity {
        val result = repository.verifyOtp(phoneNumber, otp)

        println("Received Access Token: ${result.data.accessToken}")
        println("Received Refresh Token: ${result.data.refreshToken}")
        tokenStorage.savePhoneNumber(phoneNumber)
        println("saved phone number:${tokenStorage.getPhoneNumber()}")

        tokenStorage.saveTokens(result.data.accessToken, result.data.refreshToken)

        println("Saved Access Token Check: ${tokenStorage.getAccessToken()}")
        println("Saved Refresh Token Check: ${tokenStorage.getRefreshToken()}")


        return result
    }
}

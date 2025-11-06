package com.example.qrbnb_client.data.remote.service.verifyOtpRemoteDataSource

import com.example.qrbnb_client.data.remote.model.veriftyOtpDto.VerifyOtpResponseDto
import com.example.qrbnb_client.data.remote.model.verifyotpRequestDto.VerifyOtpRequestDto

interface VerifyOtpRemoteDataSource {
    suspend fun verifyOtp(request: VerifyOtpRequestDto): VerifyOtpResponseDto
}
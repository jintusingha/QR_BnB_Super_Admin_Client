package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.otpResponsesDto.OtpResponseDto

import com.example.qrbnb_client.domain.entity.otpResponse.OtpResponse


fun OtpResponseDto.toDomain(): OtpResponse {
    return OtpResponse(
        isSuccess = success,
        message=message,
        otpExpiresIn = data.otpExpiresIn

    )
}
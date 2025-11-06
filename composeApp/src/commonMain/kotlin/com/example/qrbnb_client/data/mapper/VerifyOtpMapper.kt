package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.veriftyOtpDto.VerifyOtpResponseDto
import com.example.qrbnb_client.domain.entity.verifyotpresponse.VerifyOtpDataEntity
import com.example.qrbnb_client.domain.entity.verifyotpresponse.VerifyOtpEntity
import org.koin.compose.currentKoinScope

fun VerifyOtpResponseDto.toEntity(): VerifyOtpEntity{
    return VerifyOtpEntity(
        success= success,
        message=message,
        data= VerifyOtpDataEntity(
            userId=data.userId,
            clientId =data.clientId,
            accessToken = data.accessToken,
            refreshToken = data.refreshToken,
            expiresIn = data.expiresIn
        )
    )
}
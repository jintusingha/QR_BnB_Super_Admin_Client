package com.example.qrbnb_client.data.remote.service.qrCodeDataSource

import com.example.qrbnb_client.data.remote.model.qrCodeDto.QrCodesResponseDto

interface QrCodesDataSource {
    suspend fun getQrCodes(): QrCodesResponseDto
}
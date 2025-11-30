package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.qrCodesResponse.QrCodesResponse

interface QrCodesRepository {
    suspend fun getQrCodes(): QrCodesResponse
}
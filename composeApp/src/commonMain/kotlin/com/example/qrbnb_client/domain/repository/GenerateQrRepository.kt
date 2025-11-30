package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.generateQrEntity.GenerateQrEntity


interface GenerateQrRepository {
    suspend fun generateQr(seatingId: String): GenerateQrEntity
}
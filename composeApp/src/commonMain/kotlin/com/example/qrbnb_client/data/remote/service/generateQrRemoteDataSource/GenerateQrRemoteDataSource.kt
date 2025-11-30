package com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource

import com.example.qrbnb_client.data.remote.model.generateQrResponseDto.GenerateQrResponseDto

interface GenerateQrRemoteDataSource {
    suspend fun generateQr(seatingId: String): GenerateQrResponseDto
}
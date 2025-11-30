package com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource

import com.example.qrbnb_client.data.remote.model.generateQrResponseDto.SeatingDetailResponseDto

interface SeatingDetailRemoteDataSource {
    suspend fun getSeatingDetail(seatingId: String): SeatingDetailResponseDto
}
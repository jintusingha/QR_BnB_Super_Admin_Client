package com.example.qrbnb_client.data.remote.service.seatingListDataSource

import com.example.qrbnb_client.data.remote.model.seatingListResponseDto.SeatingListResponseDto

interface SeatingDataSource {
    suspend fun getSeatingList(): SeatingListResponseDto
}
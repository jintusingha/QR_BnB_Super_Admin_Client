package com.example.qrbnb_client.data.remote.service.seatingAreasRemoteDataSource

import com.example.qrbnb_client.data.remote.model.seatingAreasDto.SeatingAreasResponseDto

interface SeatingAreasRemoteDataSource {
    suspend fun fetchSeatingAreas(): SeatingAreasResponseDto
}
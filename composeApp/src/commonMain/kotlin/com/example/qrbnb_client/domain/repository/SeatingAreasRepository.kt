package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.seatingAreasEntity.SeatingAreasEntity

interface SeatingAreasRepository {
    suspend fun getSeatingAreas(): SeatingAreasEntity
}
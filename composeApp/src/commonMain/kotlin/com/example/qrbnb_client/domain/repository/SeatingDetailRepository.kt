package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.generateQrEntity.SeatingDetailEntity

interface SeatingDetailRepository {
    suspend fun getSeatingDetail(seatingId: String): SeatingDetailEntity
}
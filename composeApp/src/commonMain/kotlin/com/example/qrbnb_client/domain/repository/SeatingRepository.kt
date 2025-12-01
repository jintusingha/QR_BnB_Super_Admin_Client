package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.seatingEntity.SeatingEntity

interface SeatingRepository {
    suspend fun getSeatingList(): List<SeatingEntity>
}
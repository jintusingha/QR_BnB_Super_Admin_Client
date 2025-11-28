package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.data.remote.model.createSeatingDto.CreateSeatingResponseDto
import com.example.qrbnb_client.domain.entity.createSeatingEntity.CreateSeatingEntity

interface CreateSeatingRepository {
    suspend fun createSeating(entity: CreateSeatingEntity): CreateSeatingResponseDto
}
package com.example.qrbnb_client.data.remote.service.createSeatingRemoteDataSource

import com.example.qrbnb_client.data.remote.model.createSeatingDto.CreateSeatingRequestDto
import com.example.qrbnb_client.data.remote.model.createSeatingDto.CreateSeatingResponseDto

interface CreateSeatingRemoteDataSource{
    suspend fun createSeatingArea(request: CreateSeatingRequestDto): CreateSeatingResponseDto
}
package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toRequestDto
import com.example.qrbnb_client.data.remote.model.createSeatingDto.CreateSeatingResponseDto
import com.example.qrbnb_client.data.remote.service.createSeatingRemoteDataSource.CreateSeatingRemoteDataSource
import com.example.qrbnb_client.domain.entity.createSeatingEntity.CreateSeatingEntity
import com.example.qrbnb_client.domain.repository.CreateSeatingRepository

class CreateSeatingRepositoryImpl(
    private val remote: CreateSeatingRemoteDataSource
) : CreateSeatingRepository {

    override suspend fun createSeating(entity: CreateSeatingEntity): CreateSeatingResponseDto {
        return remote.createSeatingArea(entity.toRequestDto())
    }
}
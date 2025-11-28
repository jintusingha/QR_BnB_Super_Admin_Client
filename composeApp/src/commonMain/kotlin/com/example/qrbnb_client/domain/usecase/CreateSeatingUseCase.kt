package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.createSeatingEntity.CreateSeatingEntity
import com.example.qrbnb_client.domain.repository.CreateSeatingRepository

class CreateSeatingUseCase(
    private val repository: CreateSeatingRepository,
) {
    suspend operator fun invoke(entity: CreateSeatingEntity) = repository.createSeating(entity)
}

package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.seatingEntity.SeatingEntity
import com.example.qrbnb_client.domain.repository.SeatingRepository

class GetSeatingListUseCase(
    private val repository: SeatingRepository
) {
    suspend operator fun invoke(): List<SeatingEntity> {
        return repository.getSeatingList()
    }
}
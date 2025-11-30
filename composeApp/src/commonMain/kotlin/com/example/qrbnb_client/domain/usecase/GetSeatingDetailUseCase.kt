package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.generateQrEntity.SeatingDetailEntity
import com.example.qrbnb_client.domain.repository.SeatingDetailRepository

class GetSeatingDetailUseCase(
    private val repository: SeatingDetailRepository
) {
    suspend operator fun invoke(seatingId: String): SeatingDetailEntity {
        return repository.getSeatingDetail(seatingId)
    }
}
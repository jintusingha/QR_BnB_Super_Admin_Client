package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.generateQrEntity.GenerateQrEntity
import com.example.qrbnb_client.domain.repository.GenerateQrRepository

class GenerateQrUseCase(
    private val repository: GenerateQrRepository
) {
    suspend operator fun invoke(seatingId: String): GenerateQrEntity {
        return repository.generateQr(seatingId)
    }
}
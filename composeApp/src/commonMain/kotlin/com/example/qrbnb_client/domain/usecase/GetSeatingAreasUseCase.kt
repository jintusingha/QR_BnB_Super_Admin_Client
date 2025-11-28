package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.seatingAreasEntity.SeatingAreasEntity
import com.example.qrbnb_client.domain.repository.SeatingAreasRepository

class GetSeatingAreasUseCase(
    private val repository: SeatingAreasRepository,
) {
    suspend operator fun invoke(): SeatingAreasEntity = repository.getSeatingAreas()
}

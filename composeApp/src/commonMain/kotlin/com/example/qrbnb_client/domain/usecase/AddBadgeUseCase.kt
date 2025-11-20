package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeResponseDto
import com.example.qrbnb_client.domain.entity.addBadge.AddBadgeEntity
import com.example.qrbnb_client.domain.repository.AddBadgeRepository

class AddBadgeUseCase(
    private val repo: AddBadgeRepository
) {
    suspend operator fun invoke(entity: AddBadgeEntity): AddBadgeResponseDto {
        return repo.addBadge(entity)
    }
}
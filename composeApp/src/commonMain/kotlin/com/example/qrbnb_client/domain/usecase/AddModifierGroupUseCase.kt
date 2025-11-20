package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupResponseDto
import com.example.qrbnb_client.domain.entity.addModifierGroup.ModifierGroupEntity
import com.example.qrbnb_client.domain.repository.AddModifierGroupRepository

class AddModifierGroupUseCase(
    private val repository: AddModifierGroupRepository,
) {
    suspend operator fun invoke(entity: ModifierGroupEntity): AddModifierGroupResponseDto = repository.addModifierGroup(entity)
}

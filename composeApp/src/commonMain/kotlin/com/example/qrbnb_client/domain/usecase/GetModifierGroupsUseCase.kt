package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.repository.ModifierGroupsRepository


class GetModifierGroupsUseCase(
    private val repository: ModifierGroupsRepository
) {
    suspend operator fun invoke() = repository.getModifierGroups()
}
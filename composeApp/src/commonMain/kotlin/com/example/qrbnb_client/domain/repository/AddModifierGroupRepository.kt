package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupResponseDto
import com.example.qrbnb_client.domain.entity.addModifierGroup.ModifierGroupEntity

interface AddModifierGroupRepository {
    suspend fun addModifierGroup(entity: ModifierGroupEntity): AddModifierGroupResponseDto
}
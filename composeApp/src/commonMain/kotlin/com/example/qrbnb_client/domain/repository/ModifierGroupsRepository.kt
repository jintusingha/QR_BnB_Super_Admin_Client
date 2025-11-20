package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.modifierGroup.ModifierGroupResponseEntity

interface ModifierGroupsRepository {
    suspend fun getModifierGroups():List<ModifierGroupResponseEntity>
}
package com.example.qrbnb_client.data.remote.service.modifierGroupRemoteDatasource

import com.example.qrbnb_client.data.remote.model.modifierGroupDto.ModifierGroupListResponseDto

interface ModifierGroupsRemoteDataSource {
    suspend fun getModifierGroups(): ModifierGroupListResponseDto
}
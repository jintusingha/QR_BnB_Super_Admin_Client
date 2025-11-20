package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toRequestDto
import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupResponseDto
import com.example.qrbnb_client.data.remote.service.addModifierGroupRemoteDatasource.AddModifierGroupRemoteDataSource
import com.example.qrbnb_client.domain.entity.addModifierGroup.ModifierGroupEntity
import com.example.qrbnb_client.domain.repository.AddModifierGroupRepository

class AddModifierGroupRepositoryImpl (private val remote: AddModifierGroupRemoteDataSource):
    AddModifierGroupRepository {
    override suspend fun addModifierGroup(entity: ModifierGroupEntity): AddModifierGroupResponseDto {
        return remote.addModifierGroup(entity.toRequestDto())
    }
}
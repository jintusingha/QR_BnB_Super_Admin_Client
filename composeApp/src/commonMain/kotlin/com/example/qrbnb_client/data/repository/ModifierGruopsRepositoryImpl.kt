package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomainList
import com.example.qrbnb_client.data.remote.service.modifierGroupRemoteDatasource.ModifierGroupsRemoteDataSource
import com.example.qrbnb_client.domain.entity.modifierGroup.ModifierGroupResponseEntity
import com.example.qrbnb_client.domain.repository.ModifierGroupsRepository

class ModifierGroupsRepositoryImpl(
    private val remote: ModifierGroupsRemoteDataSource,
) : ModifierGroupsRepository {
    override suspend fun getModifierGroups(): List<ModifierGroupResponseEntity> = remote.getModifierGroups().data.toDomainList()
}

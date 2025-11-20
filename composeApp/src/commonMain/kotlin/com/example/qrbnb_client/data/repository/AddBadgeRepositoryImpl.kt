package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toRequestDto
import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeResponseDto
import com.example.qrbnb_client.data.remote.service.addBadgeRemoteDatasource.AddBadgeRemoteDataSource
import com.example.qrbnb_client.domain.entity.addBadge.AddBadgeEntity
import com.example.qrbnb_client.domain.repository.AddBadgeRepository

class AddBadgeRepositoryImpl(
    private val remote: AddBadgeRemoteDataSource
) : AddBadgeRepository {

    override suspend fun addBadge(entity: AddBadgeEntity): AddBadgeResponseDto {
        return remote.addBadge(entity.toRequestDto())
    }
}
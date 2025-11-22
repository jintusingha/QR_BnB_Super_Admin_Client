package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toRequestDto
import com.example.qrbnb_client.data.remote.model.addItemDto.AddItemResponseDto
import com.example.qrbnb_client.data.remote.service.addItemRemoteDatasource.AddItemRemoteDataSource
import com.example.qrbnb_client.domain.entity.addItem.AddItemEntity
import com.example.qrbnb_client.domain.repository.AddItemRepository

class AddItemRepositoryImpl (
    private val remoteDataSource: AddItemRemoteDataSource
): AddItemRepository{
    override suspend fun addItem(entity: AddItemEntity): AddItemResponseDto {
        val requestDto=entity.toRequestDto()
        return remoteDataSource.addItem(requestDto)


    }
}
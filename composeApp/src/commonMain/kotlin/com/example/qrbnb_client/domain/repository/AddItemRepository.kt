package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.data.remote.model.addItemDto.AddItemResponseDto
import com.example.qrbnb_client.domain.entity.addItem.AddItemEntity

interface AddItemRepository {
    suspend fun addItem(entity: AddItemEntity): AddItemResponseDto
}
package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.data.remote.model.addItemDto.AddItemResponseDto
import com.example.qrbnb_client.domain.entity.addItem.AddItemEntity
import com.example.qrbnb_client.domain.repository.AddItemRepository

class AddItemUseCase (
    private val repository: AddItemRepository
){
    suspend operator fun invoke(entity: AddItemEntity): AddItemResponseDto {
        return repository.addItem(entity)
    }
}
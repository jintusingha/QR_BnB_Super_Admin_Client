package com.example.qrbnb_client.data.remote.service.addItemRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addItemDto.AddItemRequestDto
import com.example.qrbnb_client.data.remote.model.addItemDto.AddItemResponseDto

interface AddItemRemoteDataSource {
    suspend fun addItem(request: AddItemRequestDto): AddItemResponseDto
}
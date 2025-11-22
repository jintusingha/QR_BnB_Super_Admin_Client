package com.example.qrbnb_client.data.remote.service.addItemRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addItemDto.AddItemRequestDto
import com.example.qrbnb_client.data.remote.model.addItemDto.AddItemResponseDto

class AddItemRemoteDataSourceImpl : AddItemRemoteDataSource{
    override suspend fun addItem(request: AddItemRequestDto): AddItemResponseDto {
        return AddItemResponseDto(
            success = true,
            message = "Mock item added Successfully"
        )
    }
}
package com.example.qrbnb_client.data.remote.service.addCategoryDatasource

import com.example.qrbnb_client.data.remote.model.addCategoryDto.AddCategoryRequestDto
import com.example.qrbnb_client.data.remote.model.addCategoryDto.AddCategoryResponseDto

interface AddCategoryRemoteDataSource {
    suspend fun addCategory(request: AddCategoryRequestDto): AddCategoryResponseDto
}
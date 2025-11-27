package com.example.qrbnb_client.data.remote.service.tagDatasource

import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseDto
import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseWrapperDto

interface TagRemoteDataSource{
    suspend fun getTags(): TagResponseWrapperDto
}
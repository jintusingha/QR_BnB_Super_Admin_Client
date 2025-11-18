package com.example.qrbnb_client.data.remote.service.tagDatasource

import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseDto

interface TagRemoteDataSource{
    suspend fun getTags():List<TagResponseDto>
}
package com.example.qrbnb_client.data.remote.service.tagDatasource

import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseDto

class TagRemoteDataSourceImpl (): TagRemoteDataSource {
    override suspend fun getTags(): List<TagResponseDto> {
        return listOf(
            TagResponseDto("1","Appetizers"),
            TagResponseDto("2","Main Courses"),
            TagResponseDto("3","Desserts")

        )
    }
}
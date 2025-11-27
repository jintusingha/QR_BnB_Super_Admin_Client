package com.example.qrbnb_client.data.remote.service.tagDatasource

import com.example.qrbnb_client.data.remote.model.tagRequest.CreateTagRequestDto
import com.example.qrbnb_client.data.remote.model.tagRequest.CreateTagResponseDto
import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseDto
import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseWrapperDto

interface TagRemoteDataSource{
    suspend fun getTags(): TagResponseWrapperDto
    suspend fun createTag(request: CreateTagRequestDto): CreateTagResponseDto
}
package com.example.qrbnb_client.data.remote.model.tagRequest

import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseDto
import kotlinx.serialization.Serializable

@Serializable
data class CreateTagResponseDto(
    val success: Boolean,
    val data: TagResponseDto?
)
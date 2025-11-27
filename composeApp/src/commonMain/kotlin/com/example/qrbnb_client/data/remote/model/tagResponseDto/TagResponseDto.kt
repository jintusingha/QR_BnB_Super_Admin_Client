package com.example.qrbnb_client.data.remote.model.tagResponseDto

import kotlinx.serialization.Serializable

@Serializable
data class TagResponseWrapperDto(
    val success: Boolean,
    val data: List<TagResponseDto>
)
@Serializable
data class TagResponseDto(
    val id: String,
    val name: String
)
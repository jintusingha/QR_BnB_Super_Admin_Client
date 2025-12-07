package com.example.qrbnb_client.data.remote.model.editTagDto

import kotlinx.serialization.Serializable
@Serializable
data class EditTagResponseDto(
    val success: Boolean,
    val data: EditTagDataDto?
)
@Serializable
data class EditTagDataDto(
    val id: String,
    val name: String
)

package com.example.qrbnb_client.data.remote.model.editTagDto

import kotlinx.serialization.Serializable

@Serializable
data class EditTagRequestDto(
    val name: String
)
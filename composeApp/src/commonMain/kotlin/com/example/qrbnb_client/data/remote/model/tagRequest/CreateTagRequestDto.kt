package com.example.qrbnb_client.data.remote.model.tagRequest

import kotlinx.serialization.Serializable

@Serializable
data class CreateTagRequestDto(
    val name: String
)
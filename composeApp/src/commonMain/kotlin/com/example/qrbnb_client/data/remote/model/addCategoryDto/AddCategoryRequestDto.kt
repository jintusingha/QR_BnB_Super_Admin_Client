package com.example.qrbnb_client.data.remote.model.addCategoryDto

import kotlinx.serialization.Serializable

@Serializable
data class AddCategoryRequestDto(
    val name:String,
    val description:String
)
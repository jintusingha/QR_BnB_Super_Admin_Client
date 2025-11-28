package com.example.qrbnb_client.data.remote.model.createSeatingDto

import kotlinx.serialization.Serializable

@Serializable
data class CreateSeatingResponseDto(
    val success:Boolean,
    val data:CreateSeatingDataDto?
)
@Serializable
data class CreateSeatingDataDto(
    val id:String
)
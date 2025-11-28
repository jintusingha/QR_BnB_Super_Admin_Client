package com.example.qrbnb_client.data.remote.model.createSeatingDto

import kotlinx.serialization.Serializable

@Serializable
data class CreateSeatingRequestDto(
    val type:String,
    val name:String,
    val description:String
)
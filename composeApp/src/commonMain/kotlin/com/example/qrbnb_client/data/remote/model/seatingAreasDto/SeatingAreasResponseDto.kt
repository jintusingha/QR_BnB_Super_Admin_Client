package com.example.qrbnb_client.data.remote.model.seatingAreasDto

import kotlinx.serialization.Serializable

@Serializable
data class SeatingAreasResponseDto(
    val success:Boolean,
    val data:List<SeatingAreaDto>
)
@Serializable
data class SeatingAreaDto(
    val id:String,
    val type:String,
    val name:String,
    val description:String,
    val capacity:Int,
    val isActive:Boolean,
    val imageUrl:String,
    val qrCodeUrl:String
)
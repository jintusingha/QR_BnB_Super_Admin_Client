package com.example.qrbnb_client.data.remote.model.addCategoryDto

import kotlinx.serialization.Serializable

@Serializable
data class AddCategoryResponseDto(
    val success:Boolean,
    val message:String,
    val data:CategoryDataDto
)
@Serializable
data class CategoryDataDto(
    val id:String,
    val name:String,
    val description:String,
    val createdAt:String
)
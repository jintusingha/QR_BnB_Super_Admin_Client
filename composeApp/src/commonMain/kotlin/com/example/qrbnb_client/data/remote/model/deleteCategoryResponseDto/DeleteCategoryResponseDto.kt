package com.example.qrbnb_client.data.remote.model.deleteCategoryResponseDto

import kotlinx.serialization.Serializable

@Serializable
data class DeleteCategoryResponseDto (
    val success:Boolean,
    val message:String
)
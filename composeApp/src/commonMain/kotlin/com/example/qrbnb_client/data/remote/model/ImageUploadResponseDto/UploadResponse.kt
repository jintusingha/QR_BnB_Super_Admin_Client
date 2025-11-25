package com.example.qrbnb_client.data.remote.model.ImageUploadResponseDto

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class UploadResponse(
    val success: Boolean,
    val message: String,
    val data: UploadData
)

@Serializable
data class UploadData(
    val url: String
)
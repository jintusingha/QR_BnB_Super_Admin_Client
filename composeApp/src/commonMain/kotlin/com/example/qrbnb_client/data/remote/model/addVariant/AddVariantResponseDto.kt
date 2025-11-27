package com.example.qrbnb_client.data.remote.model.addVariant

import kotlinx.serialization.Serializable
@Serializable
data class AddVariantResponseDto(
    val success: Boolean,
    val data: VariantDataDto?
)
@Serializable
data class VariantDataDto(
    val id: String,
    val name: String,
    val description: String,
    val options: List<VariantOptionDto>
)
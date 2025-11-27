package com.example.qrbnb_client.data.remote.model.addVariant

import kotlinx.serialization.Serializable

@Serializable
data class AddVariantRequestDto(
    val name: String,
    val description: String,
    val options: List<VariantOptionDto>
)
@Serializable
data class VariantOptionDto(
    val name: String,
    val price: Double
)
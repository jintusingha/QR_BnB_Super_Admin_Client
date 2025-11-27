package com.example.qrbnb_client.data.remote.model.addModifierGroup

import kotlinx.serialization.Serializable

@Serializable
data class AddModifierGroupRequestDto(
    val name: String,
    val selectionType: String,
    val description: String,
    val options: List<OptionDto>,
)
@Serializable
data class OptionDto(
    val name: String,
    val price: Double,
)
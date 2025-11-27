package com.example.qrbnb_client.data.remote.model.addModifierGroup
import kotlinx.serialization.Serializable
@Serializable

data class AddModifierGroupResponseDto(
    val success: Boolean,
    val data: ModifierGroupDataDto?
)
@Serializable
data class ModifierGroupDataDto(
    val id: String,
    val name: String,
    val selectionType: String,
    val description: String,
    val options: List<OptionDto>
)
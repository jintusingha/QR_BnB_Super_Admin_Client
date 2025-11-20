package com.example.qrbnb_client.data.remote.model.modifierGroupDto

data class ModifierGroupListResponseDto(
    val success: Boolean,
    val data: List<ModifierGroupDto>
)
data class ModifierGroupDto(
    val id: String,
    val name: String,
    val itemCount: Int
)
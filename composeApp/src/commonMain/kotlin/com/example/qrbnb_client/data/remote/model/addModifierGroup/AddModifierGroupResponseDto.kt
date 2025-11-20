package com.example.qrbnb_client.data.remote.model.addModifierGroup

data class AddModifierGroupResponseDto(
    val success: Boolean,
    val message: String,
    val data: ModifierGroupDataDto,
)

data class ModifierGroupDataDto(
    val groupId: String,
)

package com.example.qrbnb_client.data.remote.model.addModifierGroup

data class AddModifierGroupRequestDto(
    val groupName: String,
    val selectionType: String,
    val modifiers: List<ModifierDto>,
)

data class ModifierDto(
    val name: String,
    val price: Double,
)

package com.example.qrbnb_client.domain.entity.addModifierGroup

data class ModifierGroupEntity(
    val groupName: String,
    val selectionType: String,
    val modifiers: List<ModifierEntity>,
)

data class ModifierEntity(
    val name: String,
    val price: Double,
)
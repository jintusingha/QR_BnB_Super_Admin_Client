package com.example.qrbnb_client.data.remote.model.MenuReponseDto

import kotlinx.serialization.Serializable

@Serializable
data class MenuResponseDto(
    val success: Boolean,
    val message:String,
    val data:MenuDataDto
)

@Serializable
data class MenuDataDto(
    val categories:List<MenuCategoryDto>,
    val items:List<MenuItemDto>
)
@Serializable
data class MenuCategoryDto(
    val id:String,
    val name:String,
    val topLevelCategory:String,
    val description:String
)
@Serializable
data class MenuItemDto(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val isAvailable: Boolean,
    val categoryId: String,
    val variantOptions: List<VariantOptionDto>,
    val modifierOptions: List<ModifierOptionDto>,
    val tags: List<String>,
    val badge: String? = null
)
@Serializable
data class VariantOptionDto(
    val id: String,
    val name: String,
    val price: Double,
    val variantId: String
)

@Serializable
data class ModifierOptionDto(
    val id: String,
    val name: String,
    val price: Double,
    val groupId: String
)
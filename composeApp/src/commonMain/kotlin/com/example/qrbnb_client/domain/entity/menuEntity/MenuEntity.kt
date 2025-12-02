package com.example.qrbnb_client.domain.entity.menuEntity



data class MenuEntity(
    val categories:List<MenuCategoryEntity>,
    val items:List<MenuItemEntity>
)

data class MenuCategoryEntity(
    val id:String,
    val name:String,
    val topLevelCategory:String,
    val description:String
)

data class MenuItemEntity(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val isAvailable: Boolean,
    val categoryId: String,
    val variantOptions: List<VariantOptionEntity>,
    val modifierOptions: List<ModifierOptionEntity>,
    val tags: List<String>,
    val badge: String?
)

data class VariantOptionEntity(
    val id: String,
    val name: String,
    val price: Double,
    val variantId: String
)

data class ModifierOptionEntity(
    val id: String,
    val name: String,
    val price: Double,
    val groupId: String
)
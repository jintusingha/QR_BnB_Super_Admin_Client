package com.example.qrbnb_client.domain.entity.addItem



data class AddItemEntity(
    val name: String,
    val price: Double,
    val description: String?,
    val categoryId: String,
    val imageUrl: String?,
    val available: Boolean,
    val badges: List<String>,
    val tags: List<String>,
    val variants: List<VariantEntity>,
    val modifierGroupIds: List<String>
)

data class VariantEntity(
    val name: String,
    val price: Double? = null
)
package com.example.qrbnb_client.data.remote.model.addItemDto




data class AddItemRequestDto(
    val name:String,
    val price:Double,
    val description:String?,
    val categoryId:String,
    val imageUrl:String?,
    val available: Boolean,
    val badges:List<String>,
    val tags:List<String>,
    val variants:List<VariantOptionDto>,
    val modifierGroupIds:List<String>
)

data class VariantOptionDto(
    val name: String,
    val price: Double? = null
)
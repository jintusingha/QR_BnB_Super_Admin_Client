package com.example.qrbnb_client.domain.entity.addVariant

data class VariantEntity(
    val variantType:String,
    val options:List<VariantOptionEntity>
)
data class VariantOptionEntity(
    val name:String,
    val price: Double
)
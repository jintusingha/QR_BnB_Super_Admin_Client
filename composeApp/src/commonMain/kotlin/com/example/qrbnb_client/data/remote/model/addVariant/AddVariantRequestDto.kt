package com.example.qrbnb_client.data.remote.model.addVariant

data class AddVariantRequestDto (
    val variantType:String,
    val options:List<VariantOptionDto>
)
data class VariantOptionDto(
    val name:String,
    val price:Double
)

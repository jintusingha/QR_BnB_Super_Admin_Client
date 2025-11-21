package com.example.qrbnb_client.data.remote.model.variantsDto

data class VariantsResponseDto(
    val success:Boolean,
    val data:List<VariantDto>
)

data class VariantDto(
    val id:String,
    val name:String,
    val itemCount:Int
)
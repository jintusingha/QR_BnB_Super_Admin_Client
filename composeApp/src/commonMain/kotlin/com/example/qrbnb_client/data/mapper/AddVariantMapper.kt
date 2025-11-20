package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.addVariant.AddVariantRequestDto
import com.example.qrbnb_client.data.remote.model.addVariant.VariantOptionDto
import com.example.qrbnb_client.domain.entity.addVariant.VariantEntity
import com.example.qrbnb_client.domain.entity.addVariant.VariantOptionEntity

fun VariantEntity.toRequestDto(): AddVariantRequestDto{
    return AddVariantRequestDto(
        variantType=variantType,
        options=options.map { it.toDto() }
    )
}

fun VariantOptionEntity.toDto(): VariantOptionDto{
    return VariantOptionDto(
        name=name,
        price=price
    )
}
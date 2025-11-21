package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.variantsDto.VariantDto
import com.example.qrbnb_client.data.remote.model.variantsDto.VariantsResponseDto

import com.example.qrbnb_client.domain.entity.variantResponse.VariantsEntity


fun List<VariantDto>.toDomainList():List<VariantsEntity> =
    map{it.toDomain()}

fun VariantDto.toDomain():VariantsEntity{
    return VariantsEntity(
        id=id,
        name=name,
        itemCount=itemCount
    )
}
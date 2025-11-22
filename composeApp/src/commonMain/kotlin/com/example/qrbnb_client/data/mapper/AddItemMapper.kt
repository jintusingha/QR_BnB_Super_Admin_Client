package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.addItemDto.AddItemRequestDto
import com.example.qrbnb_client.data.remote.model.addItemDto.VariantOptionDto
import com.example.qrbnb_client.domain.entity.addItem.AddItemEntity
import com.example.qrbnb_client.domain.entity.addItem.VariantEntity

fun VariantEntity.toDto(): VariantOptionDto =
    VariantOptionDto(name, price)

fun List<VariantEntity>.toDtoList(): List<VariantOptionDto> =
    map { it.toDto() }

fun AddItemEntity.toRequestDto(): AddItemRequestDto =
    AddItemRequestDto(
        name = name,
        price = price,
        description = description,
        categoryId = categoryId,
        imageUrl = imageUrl,
        available = available,
        badges = badges,
        tags = tags,
        variants = variants.toDtoList(),
        modifierGroupIds = modifierGroupIds
    )

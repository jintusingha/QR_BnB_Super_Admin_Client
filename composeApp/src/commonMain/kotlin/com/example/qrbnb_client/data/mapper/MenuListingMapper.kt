package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.MenuReponseDto.MenuCategoryDto
import com.example.qrbnb_client.data.remote.model.MenuReponseDto.MenuItemDto
import com.example.qrbnb_client.data.remote.model.MenuReponseDto.MenuResponseDto
import com.example.qrbnb_client.data.remote.model.MenuReponseDto.ModifierOptionDto
import com.example.qrbnb_client.data.remote.model.MenuReponseDto.VariantOptionDto
import com.example.qrbnb_client.domain.entity.menuEntity.MenuCategoryEntity
import com.example.qrbnb_client.domain.entity.menuEntity.MenuEntity
import com.example.qrbnb_client.domain.entity.menuEntity.MenuItemEntity
import com.example.qrbnb_client.domain.entity.menuEntity.ModifierOptionEntity
import com.example.qrbnb_client.domain.entity.menuEntity.VariantOptionEntity

fun MenuResponseDto.toEntity(): MenuEntity {
    return MenuEntity(
        categories = data.categories.map{it.toEntity()},
        items = data.items.map{it.toEntity()}
    )
}

fun MenuCategoryDto.toEntity(): MenuCategoryEntity {
    return MenuCategoryEntity(
        id = id,
        name = name,
        topLevelCategory = topLevelCategory,
        description = description
    )
}

fun MenuItemDto.toEntity(): MenuItemEntity {
    return MenuItemEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        imageUrl = imageUrl,
        isAvailable = isAvailable,
        categoryId = categoryId,
        variantOptions = variantOptions.map { it.toEntity() },
        modifierOptions = modifierOptions.map { it.toEntity() },
        tags = tags,
        badge = badge
    )
}

fun VariantOptionDto.toEntity(): VariantOptionEntity {
    return VariantOptionEntity(
        id = id,
        name = name,
        price = price,
        variantId = variantId
    )
}

fun ModifierOptionDto.toEntity(): ModifierOptionEntity {
    return ModifierOptionEntity(
        id = id,
        name = name,
        price = price,
        groupId = groupId
    )
}
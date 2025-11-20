package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.modifierGroupDto.ModifierGroupDto
import com.example.qrbnb_client.domain.entity.addModifierGroup.ModifierGroupEntity
import com.example.qrbnb_client.domain.entity.modifierGroup.ModifierGroupResponseEntity

fun ModifierGroupDto.toDomain(): ModifierGroupResponseEntity {
    return ModifierGroupResponseEntity(
        id = id,
        name = name,
        itemCount = itemCount
    )
}

fun List<ModifierGroupDto>.toDomainList(): List<ModifierGroupResponseEntity> =
    map { it.toDomain() }
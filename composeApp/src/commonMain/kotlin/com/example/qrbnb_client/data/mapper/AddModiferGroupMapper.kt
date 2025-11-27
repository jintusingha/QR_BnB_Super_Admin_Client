package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupRequestDto

import com.example.qrbnb_client.data.remote.model.addModifierGroup.OptionDto
import com.example.qrbnb_client.domain.entity.addModifierGroup.ModifierGroupEntity
import org.koin.dsl.module

fun ModifierGroupEntity.toRequestDto(): AddModifierGroupRequestDto {
    return AddModifierGroupRequestDto(
        name = groupName,
        selectionType = selectionType,
        description = "",
        options = modifiers.map { modifier ->
            OptionDto(
                name = modifier.name,
                price = modifier.price
            )
        }
    )
}
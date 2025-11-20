package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.addModifierGroup.AddModifierGroupRequestDto
import com.example.qrbnb_client.data.remote.model.addModifierGroup.ModifierDto
import com.example.qrbnb_client.domain.entity.addModifierGroup.ModifierGroupEntity
import org.koin.dsl.module

fun ModifierGroupEntity.toRequestDto(): AddModifierGroupRequestDto{
    return AddModifierGroupRequestDto(
        groupName=groupName,
        selectionType=selectionType,
        modifiers=modifiers.map{ ModifierDto(it.name,it.price) }
    )
}
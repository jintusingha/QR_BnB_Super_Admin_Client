package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.addBadgeRequestDto.AddBadgeRequestDto
import com.example.qrbnb_client.domain.entity.addBadge.AddBadgeEntity

fun AddBadgeEntity.toRequestDto(): AddBadgeRequestDto {
    return AddBadgeRequestDto(
        name = name,
        colorHex = colorHex,
        description = description,
        icon = icon
    )
}
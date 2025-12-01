package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.seatingListResponseDto.SeatingItemDto
import com.example.qrbnb_client.domain.entity.seatingEntity.SeatingEntity

fun SeatingItemDto.toEntity(): SeatingEntity {
    return SeatingEntity(
        id = id,
        type = type,
        name = name,
        description = description,
        capacity = capacity,
        isActive = isActive,
        imageUrl = imageUrl,
        qrCodeUrl = qrCodeUrl
    )
}

fun List<SeatingItemDto>.toEntityList(): List<SeatingEntity> {
    return map { it.toEntity() }
}
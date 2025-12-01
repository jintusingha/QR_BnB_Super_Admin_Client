package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.seatingListResponseDto.SeatingItemDto
import com.example.qrbnb_client.domain.entity.seatingEntity.SeatingEntity

fun SeatingItemDto.toEntity(): SeatingEntity {
    return SeatingEntity(
        id = id,
        name = name,
        type = type,
        qrCodeUrl = qrCodeUrl,
        imageUrl = imageUrl,
        createdAt = createdAt
    )
}

fun List<SeatingItemDto>.toEntityList(): List<SeatingEntity> {
    return map { it.toEntity() }
}
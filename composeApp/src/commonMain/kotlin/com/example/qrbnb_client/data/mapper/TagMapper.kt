package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.tagResponseDto.TagResponseDto
import com.example.qrbnb_client.domain.entity.tagResponse.TagEntity

fun TagResponseDto.toDomain(): TagEntity{
    return TagEntity(
        id=id,
        name=name
    )
}

fun List<TagResponseDto>.toDomainList():List<TagEntity>{
    return map{it.toDomain()}
}
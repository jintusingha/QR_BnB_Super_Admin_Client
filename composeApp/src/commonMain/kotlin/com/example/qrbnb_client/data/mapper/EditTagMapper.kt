package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagRequestDto
import com.example.qrbnb_client.domain.entity.editTag.EditTagEntity

fun EditTagEntity.toRequestDto(): EditTagRequestDto {
    return EditTagRequestDto(
        tagId=tagId,
        newName=newName
    )
}
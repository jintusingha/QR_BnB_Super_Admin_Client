package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagResponseDto
import com.example.qrbnb_client.domain.entity.editTag.EditTagEntity

interface EditTagRepository {
    suspend fun editTag(entity: EditTagEntity): EditTagResponseDto
}
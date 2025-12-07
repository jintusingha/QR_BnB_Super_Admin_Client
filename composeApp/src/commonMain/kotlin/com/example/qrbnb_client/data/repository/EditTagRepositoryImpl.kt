package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toRequestDto
import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagRequestDto
import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagResponseDto
import com.example.qrbnb_client.data.remote.service.editTagRemoteDatasource.EditTagRemoteDataSource
import com.example.qrbnb_client.domain.entity.editTag.EditTagEntity
import com.example.qrbnb_client.domain.repository.EditTagRepository

class EditTagRepositoryImpl (private val remote: EditTagRemoteDataSource): EditTagRepository {
    override suspend fun editTag(entity: EditTagEntity): EditTagResponseDto {
        return remote.editTag(
            request = EditTagRequestDto(name = entity.newName),
            tagId = entity.tagId
        )
    }

}
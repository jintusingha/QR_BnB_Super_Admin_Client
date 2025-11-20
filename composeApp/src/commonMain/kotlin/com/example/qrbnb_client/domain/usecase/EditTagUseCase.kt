package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.data.remote.model.editTagDto.EditTagResponseDto
import com.example.qrbnb_client.domain.entity.editTag.EditTagEntity
import com.example.qrbnb_client.domain.repository.EditTagRepository

class EditTagUseCase(
    private val repository: EditTagRepository
) {
    suspend operator fun invoke(entity: EditTagEntity): EditTagResponseDto {
        return repository.editTag(entity)
    }
}
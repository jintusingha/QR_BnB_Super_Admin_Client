package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.tagResponse.TagEntity
import com.example.qrbnb_client.domain.repository.TagRepository

class CreateTagUseCase(private val repository: TagRepository) {
    suspend operator fun invoke(name: String): TagEntity {
        return repository.createTag(name)

    }
}
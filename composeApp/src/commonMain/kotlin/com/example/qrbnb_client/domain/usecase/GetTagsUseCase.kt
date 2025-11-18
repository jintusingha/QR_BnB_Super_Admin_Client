package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.tagResponse.TagEntity
import com.example.qrbnb_client.domain.repository.TagRepository

class GetTagsUseCase (private val repository: TagRepository){
    suspend operator fun invoke():List<TagEntity>{
        return repository.getTags()
    }
}
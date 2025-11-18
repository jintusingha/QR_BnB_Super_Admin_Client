package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.tagResponse.TagEntity

interface TagRepository {
    suspend fun getTags():List<TagEntity>
}
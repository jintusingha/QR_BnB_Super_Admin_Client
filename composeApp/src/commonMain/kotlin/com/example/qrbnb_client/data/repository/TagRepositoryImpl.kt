package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomainList
import com.example.qrbnb_client.data.remote.service.tagDatasource.TagRemoteDataSource
import com.example.qrbnb_client.domain.entity.tagResponse.TagEntity
import com.example.qrbnb_client.domain.repository.TagRepository

class TagRepositoryImpl (private val datasource: TagRemoteDataSource): TagRepository {
    override suspend fun getTags(): List<TagEntity> {
        val result=datasource.getTags()
        return result.data.toDomainList()

    }
}
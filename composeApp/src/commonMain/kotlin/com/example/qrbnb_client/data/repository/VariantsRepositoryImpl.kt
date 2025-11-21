package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomainList
import com.example.qrbnb_client.data.remote.service.variantRemoteDatasource.VariantRemoteDataSource

import com.example.qrbnb_client.domain.entity.variantResponse.VariantsEntity
import com.example.qrbnb_client.domain.repository.VariantsRepository

class VariantsRepositoryImpl (private val remote: VariantRemoteDataSource): VariantsRepository {
    override suspend fun getVariants(): List<VariantsEntity> {
        return remote.getVariant().data.toDomainList()
    }
}
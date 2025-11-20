package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toRequestDto
import com.example.qrbnb_client.data.remote.model.addVariant.AddVariantResponseDto
import com.example.qrbnb_client.data.remote.service.addVariantRemoteDatasource.AddVariantRemoteDataSource
import com.example.qrbnb_client.domain.entity.addVariant.VariantEntity
import com.example.qrbnb_client.domain.repository.AddVariantRepository

class AddVariantRepositoryImpl (private val remoteDataSource: AddVariantRemoteDataSource):
    AddVariantRepository {
    override suspend fun addVariant(variant: VariantEntity): AddVariantResponseDto {
        val requestDto=variant.toRequestDto()
        return remoteDataSource.addVariant(requestDto)
    }
}
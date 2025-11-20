package com.example.qrbnb_client.data.remote.service.addVariantRemoteDatasource

import com.example.qrbnb_client.data.remote.model.addVariant.AddVariantRequestDto
import com.example.qrbnb_client.data.remote.model.addVariant.AddVariantResponseDto
import com.example.qrbnb_client.domain.entity.addVariant.VariantEntity

interface AddVariantRemoteDataSource {
    suspend fun addVariant(entity: AddVariantRequestDto): AddVariantResponseDto
}
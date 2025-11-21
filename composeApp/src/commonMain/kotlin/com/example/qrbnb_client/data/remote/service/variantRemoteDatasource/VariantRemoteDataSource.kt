package com.example.qrbnb_client.data.remote.service.variantRemoteDatasource

import com.example.qrbnb_client.data.remote.model.variantsDto.VariantsResponseDto

interface VariantRemoteDataSource {
    suspend fun getVariant(): VariantsResponseDto
}
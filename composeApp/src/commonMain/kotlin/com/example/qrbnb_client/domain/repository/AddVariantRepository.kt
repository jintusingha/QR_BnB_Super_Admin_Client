package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.data.remote.model.addVariant.AddVariantResponseDto
import com.example.qrbnb_client.domain.entity.addVariant.VariantEntity

interface AddVariantRepository {
    suspend fun addVariant(variant: VariantEntity): AddVariantResponseDto
}

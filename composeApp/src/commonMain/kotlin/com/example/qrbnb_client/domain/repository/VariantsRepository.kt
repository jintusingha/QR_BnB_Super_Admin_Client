package com.example.qrbnb_client.domain.repository


import com.example.qrbnb_client.domain.entity.variantResponse.VariantsEntity

interface VariantsRepository {
    suspend fun getVariants(): List<VariantsEntity>
}
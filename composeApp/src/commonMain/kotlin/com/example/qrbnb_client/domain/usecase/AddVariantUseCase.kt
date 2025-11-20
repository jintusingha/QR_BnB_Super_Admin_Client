package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.data.remote.model.addVariant.AddVariantResponseDto
import com.example.qrbnb_client.domain.entity.addVariant.VariantEntity
import com.example.qrbnb_client.domain.repository.AddVariantRepository

class AddVariantUseCase (
    private val repository: AddVariantRepository
){
    suspend operator fun invoke(entity: VariantEntity): AddVariantResponseDto{
        return repository.addVariant(entity)
    }
}
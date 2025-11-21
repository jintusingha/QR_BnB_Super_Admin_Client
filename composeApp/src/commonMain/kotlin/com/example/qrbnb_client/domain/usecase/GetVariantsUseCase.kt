package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.addVariant.VariantEntity
import com.example.qrbnb_client.domain.repository.VariantsRepository

class GetVariantsUseCase(
    private val repository: VariantsRepository,
) {
    suspend operator fun invoke() = repository.getVariants()
}

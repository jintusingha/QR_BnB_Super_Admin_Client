package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.addVariant.VariantEntity
import com.example.qrbnb_client.domain.entity.variantResponse.VariantsEntity

sealed class VariantsUiState{
    object Loading: VariantsUiState()
    data class Success(val items:List<VariantsEntity>): VariantsUiState()
    data class Error(val message:String): VariantsUiState()
}
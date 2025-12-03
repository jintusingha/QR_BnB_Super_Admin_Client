package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.manualOrderEntity.ManualOrderEntity

sealed class ManualOrderUiState {
    object Idle : ManualOrderUiState()

    object Loading : ManualOrderUiState()

    data class Success(
        val order: ManualOrderEntity,
    ) : ManualOrderUiState()

    data class Error(
        val message: String,
    ) : ManualOrderUiState()
}

package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderDetailsEntity

sealed class OrderDetailsUiState {
    object Loading : OrderDetailsUiState()

    data class Success(
        val data: OrderDetailsEntity,
    ) : OrderDetailsUiState()

    data class Error(
        val message: String,
    ) : OrderDetailsUiState()
}

package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.orderListResponse.OrderListData

sealed interface OrdersUiState {
    data object Loading : OrdersUiState

    data class Success(
        val ordersRes: OrderListData,
    ) : OrdersUiState

    data class Error(
        val message: String,
    ) : OrdersUiState
}

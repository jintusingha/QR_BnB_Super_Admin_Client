package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.ordersByDateEntity.OrdersByDateEntity

data class OrdersByDateUiState(
    val isLoading: Boolean = false,
    val data: OrdersByDateEntity? = null,
    val errorMessage: String? = null
)
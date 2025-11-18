package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.orderReponse.OrderEntity

sealed class OrderUiState {
    object Loading: OrderUiState()
    data class Success(val orders:List<OrderEntity>): OrderUiState()
    data class Error(val message:String): OrderUiState()
}
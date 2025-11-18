package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetOrdersUseCase
import com.example.qrbnb_client.presentation.state.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<OrderUiState>(OrderUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        viewModelScope.launch {
            try {
                val orders = getOrdersUseCase()
                _uiState.value = OrderUiState.Success(orders)
            } catch (e: Exception) {
                _uiState.value = OrderUiState.Error("Failed to load orders")
            }
        }
    }
}

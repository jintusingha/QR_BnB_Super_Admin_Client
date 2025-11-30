package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetOrdersListUseCase
import com.example.qrbnb_client.domain.usecase.GetOrdersUseCase
import com.example.qrbnb_client.presentation.state.OrdersUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderListViewModel(
    private val getOrdersListUseCase: GetOrdersListUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<OrdersUiState>(OrdersUiState.Loading)
    val state: StateFlow<OrdersUiState> = _state

    fun loadOrders(
        clientId: String,
        status: String? = null,
    ) {
        viewModelScope.launch {
            _state.value = OrdersUiState.Loading
            try {
                val result = getOrdersListUseCase(clientId, status)
                _state.value =
                    OrdersUiState.Success(
                        ordersRes = result,
                    )
            } catch (e: Exception) {
                val errorMessage = e.message ?: "An unknown error occurred while loading orders."

                _state.value =
                    OrdersUiState.Error(
                        message = errorMessage,
                    )
            }
        }
    }
}

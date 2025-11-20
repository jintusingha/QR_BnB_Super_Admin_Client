package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.OrderDetailsUseCase
import com.example.qrbnb_client.presentation.state.OrderDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderDetailsViewModel (private val getOrderDetailsUseCase: OrderDetailsUseCase): ViewModel(){
    private val _uiState = MutableStateFlow<OrderDetailsUiState>(OrderDetailsUiState.Loading)
    val uiState: StateFlow<OrderDetailsUiState> = _uiState

    init{
        loadOrder("1")
    }

    fun loadOrder(orderId: String) {
        _uiState.value = OrderDetailsUiState.Loading

        viewModelScope.launch {
            try {
                val result = getOrderDetailsUseCase(orderId)
                _uiState.value = OrderDetailsUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = OrderDetailsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

}
package com.example.qrbnb_client.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetOrdersByDateUseCase
import com.example.qrbnb_client.presentation.state.OrdersByDateUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrdersCalendarViewModel(
    private val getOrdersByDateUseCase: GetOrdersByDateUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(OrdersByDateUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchOrdersByDate(date: String) {
        viewModelScope.launch {
            _uiState.value = OrdersByDateUiState(isLoading = true)

            try {
                val response = getOrdersByDateUseCase(date)
                _uiState.value = OrdersByDateUiState(
                    isLoading = false,
                    data = response
                )
            } catch (e: Exception) {
                _uiState.value = OrdersByDateUiState(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error"
                )
            }
        }
    }
}
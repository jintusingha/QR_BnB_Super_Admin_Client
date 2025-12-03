package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manulOrderRequestDto.ManualOrderRequestDto
import com.example.qrbnb_client.domain.usecase.CreateManualOrderUseCase
import com.example.qrbnb_client.presentation.state.ManualOrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ManualOrderViewModel(
    private val createOrderUseCase: CreateManualOrderUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ManualOrderUiState>(ManualOrderUiState.Idle)
    val uiState: StateFlow<ManualOrderUiState> = _uiState

    fun createOrder(request: ManualOrderRequestDto) {
        viewModelScope.launch {
            _uiState.value = ManualOrderUiState.Loading

            try {
                val result = createOrderUseCase(request)
                _uiState.value = ManualOrderUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = ManualOrderUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
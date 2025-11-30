package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetSeatingDetailUseCase
import com.example.qrbnb_client.presentation.state.SeatingDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SeatingDetailViewModel(
    private val getSeatingDetailUseCase: GetSeatingDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SeatingDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadSeatingDetail(seatingId: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    error = null
                )

                val result = getSeatingDetailUseCase(seatingId)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    seatingDetail = result
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }
}

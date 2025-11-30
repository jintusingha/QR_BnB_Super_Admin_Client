package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GenerateQrUseCase
import com.example.qrbnb_client.presentation.state.GenerateQrUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GenerateQrViewModel(
    private val generateQrUseCase: GenerateQrUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GenerateQrUiState())
    val uiState = _uiState.asStateFlow()

    fun generateQr(seatingId: String) {
        viewModelScope.launch {
            try {
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = true,
                        error = null,
                        isSuccess = false,
                    )

                val result = generateQrUseCase(seatingId)

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        isSuccess = true,
                        qrUrl = result.qrUrl,
                        deepLink = result.deepLink,
                    )
            } catch (e: Exception) {
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "Something went wrong",
                        isSuccess = false,
                    )
            }
        }
    }

    fun resetState() {
        _uiState.value = GenerateQrUiState()
    }
}

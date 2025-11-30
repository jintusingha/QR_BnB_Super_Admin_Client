package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetQrCodesUseCase
import com.example.qrbnb_client.presentation.state.QrCodesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QrCodesViewModel(
    private val getQrCodesUseCase: GetQrCodesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<QrCodesUiState>(QrCodesUiState.Loading)
    val uiState: StateFlow<QrCodesUiState> = _uiState

    fun loadQrCodes() {
        viewModelScope.launch {
            _uiState.value = QrCodesUiState.Loading

            try {
                val response = getQrCodesUseCase()
                _uiState.value = QrCodesUiState.Success(response.data)
            } catch (e: Exception) {
                _uiState.value =
                    QrCodesUiState.Error(
                        e.message ?: "Something went wrong while loading QR codes.",
                    )
            }
        }
    }
}

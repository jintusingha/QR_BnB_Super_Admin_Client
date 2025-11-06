package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.entity.verifyotpresponse.VerifyOtpEntity
import com.example.qrbnb_client.domain.usecase.VerifyOtpUseCase
import com.example.qrbnb_client.presentation.state.VerifyOtpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VerifyOtpViewModel(
    private val verifyOtpUseCase: VerifyOtpUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<VerifyOtpUiState>(VerifyOtpUiState.Idle)
    val uiState: StateFlow<VerifyOtpUiState> = _uiState

    fun verifyOtp(
        phoneNumber: String,
        otp: String,
    ) {
        viewModelScope.launch {
            _uiState.value = VerifyOtpUiState.Loading
            try {
                val result: VerifyOtpEntity = verifyOtpUseCase(phoneNumber, otp)
                _uiState.value = VerifyOtpUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = VerifyOtpUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

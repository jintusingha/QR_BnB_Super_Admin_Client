package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.entity.otpResponse.OtpResponse
import com.example.qrbnb_client.domain.usecase.SendOtpUseCase
import com.example.qrbnb_client.presentation.state.OtpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OtpViewModel(
    private val sendOtpUseCase: SendOtpUseCase,
) : ViewModel() {
    private val _otpUiState = MutableStateFlow<OtpUiState>(OtpUiState.Idle)
    val otpUiState: StateFlow<OtpUiState> = _otpUiState

    fun sendOtp(phoneNumber: String) {
        viewModelScope.launch {
            _otpUiState.value = OtpUiState.Loading
            try {
                val response = sendOtpUseCase(phoneNumber)
                _otpUiState.value = OtpUiState.Success(response)
            } catch (e: Exception) {
                _otpUiState.value = OtpUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

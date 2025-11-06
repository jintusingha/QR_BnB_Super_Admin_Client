package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.otpResponse.OtpResponse

sealed class OtpUiState {
    object Idle : OtpUiState()

    object Loading : OtpUiState()

    data class Success(
        val data: OtpResponse,
    ) : OtpUiState()

    data class Error(
        val message: String,
    ) : OtpUiState()
}

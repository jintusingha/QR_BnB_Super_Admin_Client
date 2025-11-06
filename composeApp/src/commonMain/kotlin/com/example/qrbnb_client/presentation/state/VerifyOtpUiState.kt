package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.verifyotpresponse.VerifyOtpEntity

sealed class VerifyOtpUiState{
    object Idle: VerifyOtpUiState()
    object Loading: VerifyOtpUiState()
    data class Success(val data: VerifyOtpEntity): VerifyOtpUiState()
    data class Error(val message:String): VerifyOtpUiState()


}
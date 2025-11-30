package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.qrCodesResponse.QrCodeItem

sealed class QrCodesUiState {
    object Loading : QrCodesUiState()
    data class Success(val data: List<QrCodeItem>) : QrCodesUiState()
    data class Error(val message: String) : QrCodesUiState()
}
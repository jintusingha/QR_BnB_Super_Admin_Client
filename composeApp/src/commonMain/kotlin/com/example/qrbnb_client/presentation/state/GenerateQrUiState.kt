package com.example.qrbnb_client.presentation.state

data class GenerateQrUiState(
    val isLoading: Boolean = false,
    val qrUrl: String? = null,
    val deepLink: String? = null,
    val error: String? = null,
    val isSuccess: Boolean = false
)
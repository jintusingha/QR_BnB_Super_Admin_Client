package com.example.qrbnb_client.presentation.state

data class CreateSeatingUiState(
    val isLoading: Boolean = false,
    val successId: String? = null,
    val error: String? = null
)
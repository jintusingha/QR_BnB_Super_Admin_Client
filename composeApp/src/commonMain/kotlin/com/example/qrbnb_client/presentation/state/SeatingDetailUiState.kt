package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.generateQrEntity.SeatingDetailEntity

data class SeatingDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val seatingDetail: SeatingDetailEntity? = null
)
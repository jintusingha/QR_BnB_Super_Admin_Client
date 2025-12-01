package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.seatingEntity.SeatingEntity

data class SeatingUiState(
    val isLoading: Boolean = false,
    val seatingList: List<SeatingEntity> = emptyList(),
    val error: String? = null
)
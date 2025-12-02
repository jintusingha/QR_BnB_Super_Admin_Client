package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.menuEntity.MenuEntity

sealed class MenuClientUiState {
    data object Loading : MenuClientUiState()

    data class Success(
        val menu: MenuEntity,
    ) : MenuClientUiState()

    data class Error(
        val message: String,
    ) : MenuClientUiState()
}

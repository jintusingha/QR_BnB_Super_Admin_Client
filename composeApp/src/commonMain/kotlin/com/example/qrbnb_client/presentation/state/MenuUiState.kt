package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.menuConfigurationResponse.MenuConfigItem

sealed class MenuUiState {
    object Loading : MenuUiState()
    data class Success(val items: List<MenuConfigItem>) : MenuUiState()
    data class Error(val message: String) : MenuUiState()
}
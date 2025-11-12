package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.MenuConfigurationUseCase
import com.example.qrbnb_client.presentation.state.MenuUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MenuConfigurationViewModel(
    private val getMenuConfigurationUseCase: MenuConfigurationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MenuUiState>(MenuUiState.Loading)
    val uiState: StateFlow<MenuUiState> = _uiState

    init {
        fetchMenuConfiguration()
    }

    private fun fetchMenuConfiguration() {
        viewModelScope.launch {
            try {
                val config = getMenuConfigurationUseCase()
                _uiState.value = MenuUiState.Success(config.configurations)
            } catch (e: Exception) {
                _uiState.value = MenuUiState.Error("Failed to load menu configuration")
            }
        }
    }
}
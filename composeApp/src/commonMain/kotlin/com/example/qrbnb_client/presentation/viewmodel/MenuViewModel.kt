package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetMenuUseCase
import com.example.qrbnb_client.presentation.state.MenuClientUiState
import com.example.qrbnb_client.presentation.state.MenuUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel (
    private val getMenuUseCase: GetMenuUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow<MenuClientUiState>(MenuClientUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadMenu() {
        _uiState.value = MenuClientUiState.Loading

        viewModelScope.launch {
            try {
                val menu = getMenuUseCase()
                _uiState.value = MenuClientUiState.Success(menu)
            } catch (e: Exception) {
                _uiState.value = MenuClientUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetModifierGroupsUseCase
import com.example.qrbnb_client.presentation.state.ModifierGroupsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ModifierGroupsViewModel(
    private val getModifierGroupsUseCase: GetModifierGroupsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ModifierGroupsUiState>(ModifierGroupsUiState.Loading)
    val uiState: StateFlow<ModifierGroupsUiState> = _uiState

    init {
        loadGroups()
    }

    private fun loadGroups() {
        viewModelScope.launch {
            try {
                val result = getModifierGroupsUseCase()
                _uiState.value = ModifierGroupsUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = ModifierGroupsUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}
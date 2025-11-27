package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.entity.addBadge.AddBadgeEntity
import com.example.qrbnb_client.domain.usecase.AddBadgeUseCase
import com.example.qrbnb_client.presentation.state.AddBadgeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddBadgeViewModel(
    private val useCase: AddBadgeUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddBadgeUiState>(AddBadgeUiState.Data())
    val uiState: StateFlow<AddBadgeUiState> = _uiState

    fun onNameChange(name: String) {
        val current = _uiState.value as AddBadgeUiState.Data
        _uiState.value = current.copy(name = name)
    }

    fun onColorSelected(hex: String) {
        val current = _uiState.value as AddBadgeUiState.Data
        _uiState.value = current.copy(colorHex = hex)
    }

    fun onDescriptionChange(desc: String) {
        val current = _uiState.value as AddBadgeUiState.Data
        _uiState.value = current.copy(description = desc)
    }

    fun onIconSelected(icon: String) {
        val current = _uiState.value as AddBadgeUiState.Data
        _uiState.value = current.copy(icon = icon)
    }

    fun saveBadge() {
        viewModelScope.launch {
            val current = _uiState.value as AddBadgeUiState.Data

            _uiState.value = AddBadgeUiState.Loading

            try {
                val entity =
                    AddBadgeEntity(
                        name = current.name,
                        colorHex = current.colorHex,
                        description = current.description,
                        icon = current.icon,
                    )

                val response = useCase(entity)

                if (response.success) {
                    println("Badge Added Successfully: ${response.data}")

                    _uiState.value = AddBadgeUiState.Data()
                } else {
                    _uiState.value = AddBadgeUiState.Error("Failed to add badge")
                }
            } catch (e: Exception) {
                _uiState.value = AddBadgeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.entity.addModifierGroup.ModifierEntity
import com.example.qrbnb_client.domain.entity.addModifierGroup.ModifierGroupEntity
import com.example.qrbnb_client.domain.usecase.AddModifierGroupUseCase
import com.example.qrbnb_client.presentation.state.AddModifierGroupUiState
import com.example.qrbnb_client.presentation.state.ModifierItemUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddModifierGroupViewModel(
    private val addModifierGroupUseCase: AddModifierGroupUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddModifierGroupUiState>(AddModifierGroupUiState.Data())
    val uiState: StateFlow<AddModifierGroupUiState> = _uiState

    fun onGroupNameChanged(name: String) {
        val current = _uiState.value as AddModifierGroupUiState.Data
        _uiState.value = current.copy(groupName = name)
    }

    fun onSelectionTypeChanged(type: String) {
        val current = _uiState.value as AddModifierGroupUiState.Data
        _uiState.value = current.copy(selectionType = type)
    }

    fun addModifier(
        name: String,
        price: String,
    ) {
        val current = _uiState.value as AddModifierGroupUiState.Data
        val updatedList =
            current.modifiers +
                ModifierItemUi(
                    name = name,
                    price = price,
                )
        _uiState.value = current.copy(modifiers = updatedList)
    }

    fun saveModifierGroup() {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState !is AddModifierGroupUiState.Data) return@launch

            _uiState.value = AddModifierGroupUiState.Loading

            val entity =
                ModifierGroupEntity(
                    groupName = currentState.groupName,
                    selectionType = currentState.selectionType,
                    modifiers =
                        currentState.modifiers.map {
                            ModifierEntity(
                                name = it.name,
                                price = it.price.toDoubleOrNull() ?: 0.0,
                            )
                        },
                )

            try {
                val result = addModifierGroupUseCase(entity)

                if (result.success) {
                    _uiState.value = AddModifierGroupUiState.Success("Modifier group added successfully!")

                    resetState()
                } else {
                    _uiState.value = AddModifierGroupUiState.Error(result.message)
                }
            } catch (e: Exception) {
                _uiState.value = AddModifierGroupUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _uiState.value = AddModifierGroupUiState.Data()
    }
}

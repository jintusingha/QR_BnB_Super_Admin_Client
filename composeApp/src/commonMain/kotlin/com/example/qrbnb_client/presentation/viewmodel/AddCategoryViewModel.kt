package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.AddCategoryUseCase
import com.example.qrbnb_client.presentation.state.AddCategoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddCategoryViewModel(
    private val addCategoryUseCase: AddCategoryUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddCategoryUiState>(AddCategoryUiState.Idle)
    val uiState: StateFlow<AddCategoryUiState> = _uiState

    fun addCategory(
        name: String,
        topLevelCategory: String,
        description: String,
    ) {
        viewModelScope.launch {
            _uiState.value = AddCategoryUiState.Loading
            try {
                val category = addCategoryUseCase(name, topLevelCategory, description)
                _uiState.value = AddCategoryUiState.Success("Category ${category.name} added!")
            } catch (e: Exception) {
                _uiState.value = AddCategoryUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

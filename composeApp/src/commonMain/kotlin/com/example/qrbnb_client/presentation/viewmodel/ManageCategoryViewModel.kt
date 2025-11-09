package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.di.ManageCategoryModule
import com.example.qrbnb_client.domain.entity.deleteCategoryResponse.DeleteCategoryResult
import com.example.qrbnb_client.domain.usecase.DeleteCategoryUseCase
import com.example.qrbnb_client.domain.usecase.ManageCategoryUseCase
import com.example.qrbnb_client.presentation.state.ManageCategoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ManageCategoryViewModel(
    private val manageCategoryUseCase: ManageCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ManageCategoryUiState>(ManageCategoryUiState.Loading)
    val uiState: StateFlow<ManageCategoryUiState> = _uiState

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            _uiState.value = ManageCategoryUiState.Loading
            try {
                val categories = manageCategoryUseCase()
                _uiState.value = ManageCategoryUiState.Success(categories)
            } catch (e: Exception) {
                _uiState.value =
                    ManageCategoryUiState.Error(
                        e.message ?: "Something Went Wrong",
                    )
            }
        }
    }

    fun deleteCategory(id: String) {
        viewModelScope.launch {
            try {
                val result = deleteCategoryUseCase(id)
                if (result.success) {
                    val currentList =
                        (_uiState.value as? ManageCategoryUiState.Success)?.categories
                            ?: emptyList()
                    _uiState.value =
                        ManageCategoryUiState.Success(
                            currentList.filterNot { it.id == id },
                        )
                } else {
                    _uiState.value = ManageCategoryUiState.Error(result.message)
                }
            } catch (e: Exception) {
                _uiState.value = ManageCategoryUiState.Error(e.message ?: "Failed to delete")
            }
        }
    }
}

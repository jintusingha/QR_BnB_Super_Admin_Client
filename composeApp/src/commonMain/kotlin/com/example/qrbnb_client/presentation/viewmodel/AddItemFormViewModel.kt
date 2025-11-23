package com.example.qrbnb_client.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetAddItemFormUseCase
import com.example.qrbnb_client.presentation.state.AddItemFormUiState
import kotlinx.coroutines.launch

class AddItemFormViewModel(
    private val getAddItemFormUseCase: GetAddItemFormUseCase,
) : ViewModel() {
    private val _uiState = mutableStateOf(AddItemFormUiState())
    val uiState = _uiState

    init {
        loadForm()
    }

    private fun loadForm() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val form = getAddItemFormUseCase()

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        form = form,
                        error = null,
                    )
            } catch (e: Exception) {
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "Error loading form",
                    )
            }
        }
    }

    fun onFieldValueChanged(
        fieldId: String,
        newValue: Any?,
    ) {
        val currentForm = _uiState.value.form ?: return

        val updatedFields =
            currentForm.fields.map { field ->
                if (field.id == fieldId) {
                    field.copy(value = newValue)
                } else {
                    field
                }
            }

        _uiState.value =
            _uiState.value.copy(
                form = currentForm.copy(fields = updatedFields),
            )
    }

    fun submitForm() {
    }
}

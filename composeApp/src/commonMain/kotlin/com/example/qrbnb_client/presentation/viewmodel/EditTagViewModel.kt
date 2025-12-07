package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.entity.editTag.EditTagEntity
import com.example.qrbnb_client.domain.usecase.EditTagUseCase
import com.example.qrbnb_client.presentation.state.EditTagUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditTagViewModel(
    private val editTagUseCase: EditTagUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<EditTagUiState>(EditTagUiState.Data())
    val uiState: StateFlow<EditTagUiState> = _uiState

    fun initialize(
        tagId: String,
        oldName: String,
    ) {
        _uiState.value =
            EditTagUiState.Data(
                tagId = tagId,
                tagName = oldName,
            )
    }

    fun onTagNameChanged(newName: String) {
        val current = _uiState.value
        if (current is EditTagUiState.Data) {
            _uiState.value = current.copy(tagName = newName)
        }
    }

    fun saveTag() {
        val current = _uiState.value
        if (current !is EditTagUiState.Data) return

        viewModelScope.launch {
            _uiState.value = EditTagUiState.Loading

            try {
                val entity =
                    EditTagEntity(
                        tagId = current.tagId,
                        newName = current.tagName,
                    )

                val response = editTagUseCase(entity)
                println(response)

                if (response.success) {
                    _uiState.value = EditTagUiState.Success("Tag updated successfully")
                } else {
                    _uiState.value = EditTagUiState.Error("Update failed")
                }

            } catch (e: Exception) {
                _uiState.value = EditTagUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

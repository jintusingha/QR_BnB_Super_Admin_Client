package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.entity.createSeatingEntity.CreateSeatingEntity
import com.example.qrbnb_client.domain.usecase.CreateSeatingUseCase
import com.example.qrbnb_client.presentation.state.CreateSeatingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateSeatingViewModel(
    private val createSeatingUseCase: CreateSeatingUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateSeatingUiState())
    val uiState = _uiState.asStateFlow()

    fun createSeating(
        type: String,
        name: String,
        description: String,
    ) {
        viewModelScope.launch {
            try {
                _uiState.value = CreateSeatingUiState(isLoading = true)

                val entity =
                    CreateSeatingEntity(
                        type = type,
                        name = name,
                        description = description,
                    )

                val response = createSeatingUseCase(entity)

                _uiState.value =
                    CreateSeatingUiState(
                        isLoading = false,
                        successId = response.data?.id,
                    )
            } catch (e: Exception) {
                _uiState.value =
                    CreateSeatingUiState(
                        isLoading = false,
                        error = e.message,
                    )
            }
        }
    }

    fun resetState() {
        _uiState.value = CreateSeatingUiState()
    }
}

package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetSeatingListUseCase
import com.example.qrbnb_client.presentation.state.SeatingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SeatingViewModel(
    private val getSeatingListUseCase: GetSeatingListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SeatingUiState())
    val uiState: StateFlow<SeatingUiState> = _uiState

    init {
        loadSeatingList()
    }

    fun loadSeatingList() {
        viewModelScope.launch {
            _uiState.value = SeatingUiState(isLoading = true)

            try {
                val seating = getSeatingListUseCase()
                _uiState.value = SeatingUiState(
                    isLoading = false,
                    seatingList = seating
                )
            } catch (e: Exception) {
                _uiState.value = SeatingUiState(
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }
}
package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetSeatingAreasUseCase
import com.example.qrbnb_client.presentation.state.SeatingAreasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SeatingAreasViewModel (
    private val getSeatingAreasUseCase: GetSeatingAreasUseCase

): ViewModel(){
    private val _uiState= MutableStateFlow(SeatingAreasUiState())
    val uiState=_uiState.asStateFlow()
    fun loadSeatingAreas(){
        viewModelScope.launch {
            try{
                _uiState.value= SeatingAreasUiState(isLoading = true)
                val result=getSeatingAreasUseCase()
                _uiState.value = SeatingAreasUiState(
                    rooms = result.rooms,
                    tables = result.tables,
                    isLoading = false
                )

            }catch (e: Exception) {
                _uiState.value = SeatingAreasUiState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
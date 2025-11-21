package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetVariantsUseCase
import com.example.qrbnb_client.presentation.state.VariantsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VariantsViewModel (
    private val getVariantsUseCase: GetVariantsUseCase
): ViewModel(){
    private val _uiState= MutableStateFlow<VariantsUiState>(VariantsUiState.Loading)
    val uiState: StateFlow<VariantsUiState> =_uiState

    init{
        loadVariants()
    }

    private fun loadVariants(){
        viewModelScope.launch {
            try {
                val result=getVariantsUseCase()
                _uiState.value= VariantsUiState.Success(result)
            }catch (e: Exception){
                _uiState.value= VariantsUiState.Error(e.message?:"Unknown Error")
            }
        }
    }
}
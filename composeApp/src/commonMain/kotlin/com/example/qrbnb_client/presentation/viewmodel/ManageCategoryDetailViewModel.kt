package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetManageCategoryDetailUseCase
import com.example.qrbnb_client.presentation.state.ManageCategoryDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ManageCategoryDetailViewModel(
    private val getManageCategoryDetailsUseCase: GetManageCategoryDetailUseCase,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<ManageCategoryDetailsUiState>(ManageCategoryDetailsUiState.Loading)

    val uiState: StateFlow<ManageCategoryDetailsUiState> = _uiState
    fun loadManageCategoryDetails(){
        viewModelScope.launch {
            try{
                _uiState.value= ManageCategoryDetailsUiState.Loading
                val categories=getManageCategoryDetailsUseCase()
                _uiState.value= ManageCategoryDetailsUiState.Success(categories)
            }catch(e: Exception){
                _uiState.value= ManageCategoryDetailsUiState.Error(e.message?:"Unknown error")
            }
        }
    }
}

package com.example.qrbnb_client.presentation.state

sealed class AddCategoryUiState{
    object Idle: AddCategoryUiState()
    object Loading: AddCategoryUiState()
    data class Success(val message:String): AddCategoryUiState()
    data class Error(val message:String): AddCategoryUiState()
}
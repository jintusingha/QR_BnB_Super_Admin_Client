package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.manageCategoryResponse.Category

sealed class ManageCategoryUiState{
    object Loading: ManageCategoryUiState()
    data class Success(val categories:List<Category>): ManageCategoryUiState()
    data class Error(val message:String): ManageCategoryUiState()
}
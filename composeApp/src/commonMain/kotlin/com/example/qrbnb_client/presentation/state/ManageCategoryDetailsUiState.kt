package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.manageCategoryDetailsResponse.ManageCategoryDetails

sealed class ManageCategoryDetailsUiState{
    object Loading: ManageCategoryDetailsUiState()
    data class Success(val categories:List<ManageCategoryDetails>): ManageCategoryDetailsUiState()
    data class Error(val message:String): ManageCategoryDetailsUiState()
}
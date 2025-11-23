package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.AddItemsResponse.DynamicFormEntity

data class AddItemFormUiState(
    val isLoading: Boolean = false,
    val form: DynamicFormEntity? = null,
    val error: String? = null
)
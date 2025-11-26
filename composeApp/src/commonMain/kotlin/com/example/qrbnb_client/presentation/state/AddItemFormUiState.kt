package com.example.qrbnb_client.presentation.state

import com.example.qrbnb_client.domain.entity.AddItemsResponse.DynamicFormEntity

data class AddItemFormUiState(
    val isLoading: Boolean = false,
    val form: DynamicFormEntity? = null,
    val isSubmitting: Boolean = false,
    val error: String? = null,
    val message: String? = null     // <-- ADD THIS
)
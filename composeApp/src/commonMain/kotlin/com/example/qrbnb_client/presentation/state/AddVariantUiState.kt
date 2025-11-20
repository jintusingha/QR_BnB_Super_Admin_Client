package com.example.qrbnb_client.presentation.state

sealed class AddVariantUiState {
    object Loading : AddVariantUiState()

    data class Data(
        val variantType: String = "",
        val options: List<VariantOptionUi> = emptyList(),
    ) : AddVariantUiState()

    data class Success(
        val message: String,
    ) : AddVariantUiState()

    data class Error(
        val message: String,
    ) : AddVariantUiState()
}

data class VariantOptionUi(
    val name: String,
    val price: String?,
)

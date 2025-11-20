package com.example.qrbnb_client.presentation.state

sealed class AddBadgeUiState {
    object Loading : AddBadgeUiState()
    data class Success(val msg: String) : AddBadgeUiState()
    data class Error(val msg: String) : AddBadgeUiState()
    data class Data(
        val name: String = "",
        val colorHex: String = "",
        val description: String = "",
        val icon: String? = null
    ) : AddBadgeUiState()
}
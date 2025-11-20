package com.example.qrbnb_client.presentation.state

sealed class EditTagUiState {
    object Loading : EditTagUiState()

    data class Data(
        val tagId: String = "",
        val tagName: String = "",
    ) : EditTagUiState()

    data class Error(
        val message: String,
    ) : EditTagUiState()

    data class Success(
        val message: String,
    ) : EditTagUiState()
}

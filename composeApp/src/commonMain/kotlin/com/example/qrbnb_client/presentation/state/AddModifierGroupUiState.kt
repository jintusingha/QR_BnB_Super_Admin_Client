package com.example.qrbnb_client.presentation.state

sealed class AddModifierGroupUiState {
    object Loading : AddModifierGroupUiState()

    data class Success(
        val message: String,
    ) : AddModifierGroupUiState()

    data class Error(
        val message: String,
    ) : AddModifierGroupUiState()

    data class Data(
        val groupName: String = "",
        val selectionType: String = "",
        val modifiers: List<ModifierItemUi> = emptyList(),
    ) : AddModifierGroupUiState()
}

data class ModifierItemUi(
    val name: String = "",
    val price: String = "",
)

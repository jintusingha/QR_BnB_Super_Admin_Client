package com.example.qrbnb_client.presentation.state

sealed class AddItemUiState {
    object Loading : AddItemUiState()
    data class Error(val message: String) : AddItemUiState()
    data class Success(val message: String) : AddItemUiState()
    data class Data(val form: AddItemFormData) : AddItemUiState()
}
data class AddItemFormData(
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val categoryId: String = "",
    val imageUrl: String = "",
    val available: Boolean = true,
    val selectedBadges: List<String> = emptyList(),
    val selectedTags: List<String> = emptyList(),
    val selectedModifierGroups: List<String> = emptyList(),
    val variants: List<VariantUi> = emptyList()
)

data class VariantUi(
    val name: String,
    val price: String? = null
)
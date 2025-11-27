package com.example.qrbnb_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.entity.addVariant.VariantEntity
import com.example.qrbnb_client.domain.entity.addVariant.VariantOptionEntity
import com.example.qrbnb_client.domain.usecase.AddVariantUseCase
import com.example.qrbnb_client.presentation.state.AddVariantUiState
import com.example.qrbnb_client.presentation.state.VariantOptionUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddVariantViewModel(
    private val addVariantUseCase: AddVariantUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddVariantUiState>(AddVariantUiState.Data())
    val uiState: StateFlow<AddVariantUiState> = _uiState

    fun onVariantTypeChanged(type: String) {
        val current = _uiState.value
        if (current !is AddVariantUiState.Data) return

        _uiState.value = current.copy(variantType = type)
    }

    fun addOption(
        name: String,
        price: String?,
    ) {
        val current = _uiState.value
        if (current !is AddVariantUiState.Data) return

        val updated = current.options + VariantOptionUi(name, price)
        _uiState.value = current.copy(options = updated)
    }

    fun deleteOption(option: VariantOptionUi) {
        val current = _uiState.value
        if (current !is AddVariantUiState.Data) return

        _uiState.value = current.copy(options = current.options - option)
    }

    fun saveVariant() {
        viewModelScope.launch {
            val current = _uiState.value
            if (current !is AddVariantUiState.Data) return@launch

            _uiState.value = AddVariantUiState.Loading

            val entity =
                VariantEntity(
                    variantType = current.variantType,
                    options =
                        current.options.map {
                            VariantOptionEntity(
                                name = it.name,
                                price = it.price?.toDoubleOrNull() ?: 0.0,
                            )
                        },
                )

            try {
                val result = addVariantUseCase(entity)

                if (result.success) {
                    _uiState.value = AddVariantUiState.Success("Variant added successfully")
                } else {
                    _uiState.value =
                        AddVariantUiState.Data(
                            variantType = current.variantType,
                            options = current.options,
                        )

                    _uiState.value = AddVariantUiState.Error("Failed to add variant")
                }
            } catch (e: Exception) {
                _uiState.value =
                    AddVariantUiState.Data(
                        variantType = current.variantType,
                        options = current.options,
                    )

                _uiState.value = AddVariantUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _uiState.value = AddVariantUiState.Data()
    }
}

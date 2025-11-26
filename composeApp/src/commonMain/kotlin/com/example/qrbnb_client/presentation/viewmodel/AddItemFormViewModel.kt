package com.example.qrbnb_client.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrbnb_client.domain.usecase.GetAddItemFormUseCase
import com.example.qrbnb_client.domain.usecase.SubmitItemUseCase
import com.example.qrbnb_client.domain.usecase.UploadImageUseCase
import com.example.qrbnb_client.presentation.state.AddItemFormUiState
import kotlinx.coroutines.launch

class AddItemFormViewModel(
    private val getAddItemFormUseCase: GetAddItemFormUseCase,
    private val uploadImageUseCase: UploadImageUseCase,
    private val submitItemUseCase: SubmitItemUseCase,
) : ViewModel() {
    private val _uiState = mutableStateOf(AddItemFormUiState())
    val uiState = _uiState

    init {
        loadForm()
    }

    private fun loadForm() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val form = getAddItemFormUseCase()

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        form = form,
                        error = null,
                    )
            } catch (e: Exception) {
                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "Error loading form",
                    )
            }
        }
    }

    fun onFieldValueChanged(
        fieldId: String,
        newValue: Any?,
    ) {
        val currentForm = _uiState.value.form ?: return

        val updatedFields =
            currentForm.fields.map { field ->
                if (field.id == fieldId) {
                    field.copy(value = newValue)
                } else {
                    field
                }
            }

        _uiState.value =
            _uiState.value.copy(
                form = currentForm.copy(fields = updatedFields),
            )
    }

    fun uploadImage(
        fieldId: String,
        localUri: String,
    ) {
        viewModelScope.launch {
            println(" Upload Started for fieldId=$fieldId, uri=$localUri")

            try {
                val uploadedUrl = uploadImageUseCase(localUri)

                println(" Upload Success! URL = $uploadedUrl")

                onFieldValueChanged(fieldId, uploadedUrl)
            } catch (e: Exception) {
                println(" Upload Failed: ${e.message}")
                _uiState.value =
                    _uiState.value.copy(
                        error = e.message ?: "Image upload failed",
                    )
            }
        }
    }

    fun submitForm() {
        val currentForm = _uiState.value.form ?: return

        println("---- SUBMIT STARTED ----")

        viewModelScope.launch {
            _uiState.value =
                _uiState.value.copy(
                    isSubmitting = true,
                    error = null,
                    message = null,
                )

            try {
                val values =
                    currentForm.fields.associate { field ->
                        field.id to field.value
                    }

                println("FORM ID: ${currentForm.formId}")
                println("VALUES: $values")

                submitItemUseCase(
                    formId = currentForm.formId,
                    values = values,
                )

                println("SUBMIT SUCCESS")

                val clearedFields =
                    currentForm.fields.map { field ->
                        val resetValue: Any? =
                            when (field.type.lowercase()) {
                                "switch" -> field.defaultValue ?: false
                                else -> null
                            }
                        field.copy(value = resetValue)
                    }

                _uiState.value =
                    _uiState.value.copy(
                        isSubmitting = false,
                        form = currentForm.copy(fields = clearedFields),
                        message = "Item added successfully!",
                    )
            } catch (e: Exception) {
                println(" SUBMIT FAILED")
                println("ERROR: ${e.message}")

                _uiState.value =
                    _uiState.value.copy(
                        isSubmitting = false,
                        error = e.message ?: "Submit failed",
                    )
            }
        }
    }

    fun clearMessage() {
        _uiState.value = _uiState.value.copy(message = null)
    }
}

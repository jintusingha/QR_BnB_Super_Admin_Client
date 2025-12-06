package com.example.qrbnb_client.presentation.viewmodel

import com.example.qrbnb_client.data.FileData
import com.example.qrbnb_client.data.IFilePicker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UploadMenuViewModel(
    private val filePicker: IFilePicker,
) {
    private val _state = MutableStateFlow<String>("No file selected")
    val state: StateFlow<String> = _state

    private val _selectedFile = MutableStateFlow<FileData?>(null)
    val selectedFile: StateFlow<FileData?> = _selectedFile

    fun onFilePicked(fileData: FileData?) {
        if (fileData == null) {
            _state.value = "No file selected"
            _selectedFile.value = null
            return
        }

        val error = validateFile(fileData)

        if (error != null) {
            _state.value = "Error: $error"
            _selectedFile.value = null
        } else {
            _state.value = " ${fileData.name} selected"
            _selectedFile.value = fileData
            filePicker.setPickedFile(fileData)
        }
    }

    private fun validateFile(fileData: FileData): String? {
        val maxSize = 25 * 1024 * 1024
        if (fileData.size > maxSize) {
            return "File is too big! Must be under 25MB"
        }

        val allowedTypes =
            listOf(
                "application/pdf",
                "image/jpeg",
                "image/jpg",
                "image/png",
            )

        if (!allowedTypes.contains(fileData.mimeType.lowercase())) {
            return "Wrong file type! Only PDF, JPG, PNG allowed"
        }

        return null
    }
}

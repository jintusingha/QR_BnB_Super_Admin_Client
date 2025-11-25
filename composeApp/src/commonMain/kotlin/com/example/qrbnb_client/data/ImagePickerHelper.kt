package com.example.qrbnb_client.data

import androidx.compose.runtime.Composable



interface ImagePickerHelper {
    @Composable
    fun rememberImagePicker(onImageSelected: (String) -> Unit): () -> Unit
    // ⭐ REMOVED @Composable from the lambda parameter
}
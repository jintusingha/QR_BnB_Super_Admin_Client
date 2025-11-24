package com.example.qrbnb_client.data

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

class AndroidImagePickerHelper : ImagePickerHelper {

    @Composable
    override fun rememberImagePicker(onImageSelected: (String) -> Unit): () -> Unit {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: android.net.Uri? ->
            uri?.let {
                onImageSelected(it.toString())
            }
        }

        return { launcher.launch("image/*") }
    }
}
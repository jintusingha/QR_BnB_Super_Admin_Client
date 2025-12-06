package com.example.qrbnb_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import org.koin.android.ext.android.get
import com.example.qrbnb_client.data.IFilePicker
import com.example.qrbnb_client.data.AndroidFilePicker
import com.example.qrbnb_client.presentation.viewmodel.UploadMenuViewModel


import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import org.koin.android.ext.android.get


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val picker = get<IFilePicker>() as AndroidFilePicker
        val viewModel = get<UploadMenuViewModel>()

        val launcher = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            val fileData = uri?.let { picker.readFileFromUri(it) }


            viewModel.onFilePicked(fileData)
        }

        picker.onLaunchPickerRequest = {
            launcher.launch("*/*")
        }

        setContent {
            App()
        }
    }
}
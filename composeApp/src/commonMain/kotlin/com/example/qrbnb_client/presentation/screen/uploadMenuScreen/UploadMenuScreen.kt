package com.example.qrbnb_client.presentation.screen.uploadMenuScreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.data.IFilePicker
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.viewmodel.UploadMenuViewModel
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_700
import com.example.qrbnb_client.ui.style_18_23_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@Composable
fun UploadMenuScreen(
    onBack: () -> Unit = {}
) {
    val viewModel: UploadMenuViewModel = koinInject()
    val filePicker = koinInject<IFilePicker>()

    val state by viewModel.state.collectAsState()
    val selectedFile by viewModel.selectedFile.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Upload Menu",
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(2.dp, Color.LightGray, RoundedCornerShape(12.dp))
                    .background(Color(0xFFFAFAFA), RoundedCornerShape(12.dp))
                    .padding(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Drag and drop to upload",
                        style= style_18_23_700(),
                        color=Black
                    )

                    Text(
                        text = "Upload a clear PDF or image of your menu. Accepted formats: PDF, JPG, PNG. Max file size: 25MB",
                        style= style_14_21_400(),
                        textAlign = TextAlign.Center
                    )

                    Button(
                        onClick = { filePicker.launchFilePicker() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF5F0F0)
                        )
                    ) {
                        Text(
                            text = "Choose File",
                            style= style_14_21_700(),
                            color = Black
                        )
                    }

                    Text(
                        text = state,
                        fontSize = 14.sp,
                        color = if (state.startsWith("Error")) Color.Red else Black,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Process with AI */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B6B)
                ),
                shape = RoundedCornerShape(8.dp),
                enabled = selectedFile != null
            ) {
                Text(
                    text = "Process with AI",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

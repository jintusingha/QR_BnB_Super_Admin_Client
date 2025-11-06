package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qrbnb_client.presentation.reusableComponents.CustomInputField
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.reusableComponents.PrimaryActionButton
import com.example.qrbnb_client.presentation.state.OtpUiState
import com.example.qrbnb_client.presentation.viewmodel.OtpViewModel
import com.example.qrbnb_client.ui.Otp_button_background
import org.koin.compose.koinInject

@Composable
fun OtpScreen(
    viewModel: OtpViewModel = koinInject(),
    onBack: () -> Unit = {},
    onOtpSentSuccess: (String) -> Unit = {},
) {
    val uiState by viewModel.otpUiState.collectAsState()
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Client Login",
                navigationIcon = {},
                actions = {},
            )
        },
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            CustomInputField(
                label = "Phone Number",
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryActionButton(
                text = "SEND OTP",
                onClick = { viewModel.sendOtp(phoneNumber) },
                backgroundColor = Otp_button_background,
                isLoading = uiState is OtpUiState.Loading,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            when (val state = uiState) {
                is OtpUiState.Error ->
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                    )

                is OtpUiState.Success -> {
                    LaunchedEffect(key1 = state) {
                        onOtpSentSuccess(phoneNumber)
                    }

                    Text(
                        text = state.data.message,
                        color = Color(0xFF00C853),
                    )
                }

                else -> {}
            }
        }
    }
}

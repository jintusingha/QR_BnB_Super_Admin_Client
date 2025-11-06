package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.example.qrbnb_client.presentation.reusableComponents.CustomInputField
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.reusableComponents.PrimaryActionButton
import com.example.qrbnb_client.presentation.state.OtpUiState
import com.example.qrbnb_client.presentation.state.VerifyOtpUiState
import com.example.qrbnb_client.presentation.viewmodel.VerifyOtpViewModel
import com.example.qrbnb_client.ui.Otp_button_background
import com.example.qrbnb_client.ui.jakarta_regular_14px
import com.example.qrbnb_client.ui.resend_otp_text_color
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpVerificationScreen(
    phoneNumber: String,
    viewModel: VerifyOtpViewModel = koinInject(),
    onNavigateBack: () -> Unit,
) {
    var otp by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsState()

    val isLoading = uiState is VerifyOtpUiState.Loading

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Client Login",
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "LeftArrow",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
        containerColor = Color.White,
    ) { paddingvalues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingvalues)
                    .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            CustomInputField(
                label = "Enter Otp",
                value = otp,
                onValueChange = { otp = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Resend Otp",
                style = jakarta_regular_14px(),
                color = resend_otp_text_color
            )
            Spacer(modifier = Modifier.height(12.dp))

            PrimaryActionButton(
                text = "Verify & Continue",
                onClick = { viewModel.verifyOtp(phoneNumber, otp) },
                backgroundColor = Otp_button_background,
                isLoading = false,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            when (val state = uiState) {
                is VerifyOtpUiState.Idle -> {
                }

                is VerifyOtpUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }

                is VerifyOtpUiState.Success -> {
                    Text(
                        text = "OTP Verified Successfully!",
                        color = Color(0xFF2E7D32),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                is VerifyOtpUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

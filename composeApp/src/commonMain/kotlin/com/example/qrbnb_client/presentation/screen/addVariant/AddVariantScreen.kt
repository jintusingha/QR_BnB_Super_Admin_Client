package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.AddVariantUiState
import com.example.qrbnb_client.presentation.viewmodel.AddVariantViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_700
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500
import com.example.qrbnb_client.ui.style_18_23_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVariantScreen(
    viewModel: AddVariantViewModel = koinInject(),
    onBack: () -> Unit,
    onSuccess: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    var optionName by remember { mutableStateOf("") }
    var optionPrice by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is AddVariantUiState.Success -> {
                onSuccess()
                optionName = ""
                optionPrice = ""
                viewModel.resetState()
            }
            else -> {}
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomTopAppBar(
                title = "Add Variant",
                navigationIcon = {
                    IconButton(onClick = {onBack()}) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
    ) { padding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
        ) {
            when (val state = uiState) {
                is AddVariantUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                is AddVariantUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                is AddVariantUiState.Data -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(16.dp),
                    ) {
                        Text(
                            text = "Variant Type",
                            style = style_16_24_500(),
                            modifier = Modifier.padding(bottom = 8.dp),
                        )

                        OutlinedTextField(
                            value = state.variantType,
                            onValueChange = { viewModel.onVariantTypeChanged(it) },
                            placeholder = {
                                Text(
                                    "e.g. Size",
                                    style = style_16_24_400(),
                                    color = SoftBrown,
                                )
                            },
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 24.dp),
                            colors =
                                OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = Color(0xFFE0E0E0),
                                    focusedBorderColor = Color(0xFFFF6B6B),
                                ),
                            shape = RoundedCornerShape(12.dp),
                        )

                        Text(
                            text = "Options List",
                            style = style_18_23_700(),
                            modifier = Modifier.padding(bottom = 16.dp),
                        )

                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Option Name",
                                    style = style_16_24_500(),
                                    modifier = Modifier.padding(bottom = 6.dp),
                                )
                                OutlinedTextField(
                                    value = optionName,
                                    onValueChange = { optionName = it },
                                    placeholder = {
                                        Text(
                                            "e.g. Small",
                                            style = style_16_24_400(),
                                            color = SoftBrown,
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors =
                                        OutlinedTextFieldDefaults.colors(
                                            unfocusedBorderColor = Color(0xFFE0E0E0),
                                            focusedBorderColor = Color(0xFFFF6B6B),
                                        ),
                                    shape = RoundedCornerShape(12.dp),
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Price (Optional)",
                                    style = style_16_24_500(),
                                    modifier = Modifier.padding(bottom = 6.dp),
                                )
                                OutlinedTextField(
                                    value = optionPrice,
                                    onValueChange = { optionPrice = it },
                                    placeholder = {
                                        Text(
                                            "e.g. 2.50",
                                            style = style_16_24_400(),
                                            color = SoftBrown,
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors =
                                        OutlinedTextFieldDefaults.colors(
                                            unfocusedBorderColor = Color(0xFFE0E0E0),
                                            focusedBorderColor = Color(0xFFFF6B6B),
                                        ),
                                    shape = RoundedCornerShape(12.dp),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Surface(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .clickable {
                                        if (optionName.isNotBlank()) {
                                            viewModel.addOption(
                                                optionName,
                                                optionPrice.ifBlank { null },
                                            )
                                            optionName = ""
                                            optionPrice = ""
                                        }
                                    },
                            color = Color(0xFFF5F0F0),
                            shape = RoundedCornerShape(24.dp),
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "+ Add Option",
                                    style = style_14_21_700(),
                                    color = Color.Black,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        state.options.forEach { option ->
                            Card(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                colors =
                                    CardDefaults.cardColors(
                                        containerColor = Color(0xFFF5F5F5),
                                    ),
                            ) {
                                Row(
                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = option.name,
                                            fontWeight = FontWeight.Medium,
                                        )
                                        option.price?.let {
                                            Text(
                                                text = "Price: $$it",
                                                fontSize = 12.sp,
                                                color = Color.Gray,
                                            )
                                        }
                                    }
                                    IconButton(
                                        onClick = { viewModel.deleteOption(option) },
                                    ) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = Color.Gray,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                is AddVariantUiState.Success -> Unit
            }

            if (uiState is AddVariantUiState.Data || uiState is AddVariantUiState.Success) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        modifier =
                            Modifier
                                .height(40.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .clickable { onBack() }
                                .padding(horizontal = 0.dp),
                        color = Color(0xFFF5F0F0),
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .padding(horizontal = 20.dp)
                                    .fillMaxHeight(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "Cancel",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                            )
                        }
                    }

                    Button(
                        onClick = { viewModel.saveVariant() },
                        enabled = uiState !is AddVariantUiState.Loading,
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF5252),
                            ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.height(40.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp),
                    ) {
                        Text(
                            text = "Save",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}

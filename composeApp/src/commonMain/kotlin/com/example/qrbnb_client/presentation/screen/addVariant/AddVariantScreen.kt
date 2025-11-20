package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.presentation.state.AddVariantUiState
import com.example.qrbnb_client.presentation.state.VariantOptionUi
import com.example.qrbnb_client.presentation.viewmodel.AddVariantViewModel
import org.koin.compose.koinInject

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
                println("Success: ${state.message}")

                onSuccess()

                optionName = ""
                optionPrice = ""

                viewModel.resetState()
            }
            is AddVariantUiState.Error -> {
                println("❌ Error: ${state.message}")
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Variant") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                    ),
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
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
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
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 8.dp),
                        )

                        OutlinedTextField(
                            value = state.variantType,
                            onValueChange = { viewModel.onVariantTypeChanged(it) },
                            placeholder = { Text("e.g. Size", color = Color.Gray) },
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 24.dp),
                            colors =
                                OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = Color.LightGray,
                                ),
                        )

                        Text(
                            text = "Options List",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
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
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(bottom = 4.dp),
                                )
                                OutlinedTextField(
                                    value = optionName,
                                    onValueChange = { optionName = it },
                                    placeholder = { Text("e.g. Small", color = Color.Gray) },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors =
                                        OutlinedTextFieldDefaults.colors(
                                            unfocusedBorderColor = Color.LightGray,
                                        ),
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Price (Optional)",
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(bottom = 4.dp),
                                )
                                OutlinedTextField(
                                    value = optionPrice,
                                    onValueChange = { optionPrice = it },
                                    placeholder = { Text("e.g. 2.50", color = Color.Gray) },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors =
                                        OutlinedTextFieldDefaults.colors(
                                            unfocusedBorderColor = Color.LightGray,
                                        ),
                                )
                            }
                        }

                        TextButton(
                            onClick = {
                                if (optionName.isNotBlank()) {
                                    viewModel.addOption(
                                        optionName,
                                        optionPrice.ifBlank { null },
                                    )
                                    optionName = ""
                                    optionPrice = ""
                                }
                            },
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                        ) {
                            Text("+ Add Option")
                        }

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
                                    IconButton(onClick = { viewModel.deleteOption(option) }) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = Color.Gray,
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                is AddVariantUiState.Success -> { /* Handled by LaunchedEffect */ }
            }

            if (uiState is AddVariantUiState.Data || uiState is AddVariantUiState.Success) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    OutlinedButton(
                        onClick = onBack,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = { viewModel.saveVariant() },
                        modifier = Modifier.weight(1f),
                        enabled = uiState !is AddVariantUiState.Loading,
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF5252),
                            ),
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

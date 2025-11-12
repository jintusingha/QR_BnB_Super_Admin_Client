package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.AddCategoryUiState
import com.example.qrbnb_client.presentation.viewmodel.AddCategoryViewModel
import com.example.qrbnb_client.ui.RosyBrown
import com.example.qrbnb_client.ui.body16Regular
import com.example.qrbnb_client.ui.style_16_24_500
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategoryScreen(
    viewModel: AddCategoryViewModel = koinInject(),
    onNavigateBack: () -> Unit = {},
    onCategoryAdded: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var categoryName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState is AddCategoryUiState.Success) {
            showSuccessDialog = true
        }
    }

    if (showSuccessDialog && uiState is AddCategoryUiState.Success) {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Success") },
            text = { Text((uiState as AddCategoryUiState.Success).message) },
            confirmButton = {
                TextButton(onClick = {
                    showSuccessDialog = false
                    onCategoryAdded()
                }) {
                    Text("OK")
                }
            },
        )
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomTopAppBar(
                title = "Add Category",
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "left arrow icon",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(24.dp),
            ) {
                Text(
                    text = "Category Name*",
                    style = style_16_24_500(),
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = categoryName,
                    onValueChange = { categoryName = it },
                    placeholder = {
                        Text(
                            text = "Enter category name",
                            style = body16Regular(),
                            color = RosyBrown,
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            focusedBorderColor = Color(0xFFFF6B6B),
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                        ),
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Description",
                    style = style_16_24_500(),
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = {
                        Text(
                            text = "Add a description (optional)",
                            style = body16Regular(),
                            color = RosyBrown,
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            focusedBorderColor = Color(0xFFFF6B6B),
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                        ),
                    maxLines = 5,
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        if (categoryName.isNotBlank()) {
                            viewModel.addCategory(categoryName.trim(), description.trim())
                        }
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF75C5C),
                            disabledContainerColor = Color(0xFFFFB3B3),
                        ),
                    enabled = categoryName.isNotBlank() && uiState !is AddCategoryUiState.Loading,
                ) {
                    if (uiState is AddCategoryUiState.Loading && categoryName.isNotBlank()) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp,
                        )
                    } else {
                        Text(
                            text = "Save",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = onNavigateBack,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                    enabled = uiState !is AddCategoryUiState.Loading,
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                }
            }

            if (uiState is AddCategoryUiState.Error) {
                Card(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .align(Alignment.TopCenter),
                    colors =
                        CardDefaults.cardColors(
                            containerColor = Color(0xFFFFEBEE),
                        ),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "something wrong",
                            fontSize = 20.sp,
                        )
                        Text(
                            text = (uiState as AddCategoryUiState.Error).message,
                            fontSize = 14.sp,
                            color = Color(0xFFC62828),
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
        }
    }
}

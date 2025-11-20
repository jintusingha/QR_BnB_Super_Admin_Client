package com.example.qrbnb_client.presentation.screen.addBadgeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.reusableComponents.PrimaryActionButton
import com.example.qrbnb_client.presentation.state.AddBadgeUiState
import com.example.qrbnb_client.presentation.viewmodel.AddBadgeViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBadgeScreen(
    viewModel: AddBadgeViewModel = koinInject(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Add Badge", navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(Res.drawable.leftArrowIcon),
                        "left arrow icon",
                        modifier = Modifier.size(24.dp),
                    )
                }
            })
        },
    ) { paddingValues ->
        when (val state = uiState) {
            is AddBadgeUiState.Loading -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            is AddBadgeUiState.Error -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = state.msg, color = Color.Red)
                }
            }

            is AddBadgeUiState.Data -> {
                AddBadgeContent(
                    state = state,
                    onNameChange = { viewModel.onNameChange(it) },
                    onColorSelected = { hex -> viewModel.onColorSelected(hex) },
                    onDescriptionChange = { viewModel.onDescriptionChange(it) },
                    onIconChange = { viewModel.onIconSelected(it) },
                    onSaveClick = { viewModel.saveBadge() },
                    onCancelClick = onBackClick,
                    modifier = Modifier.padding(paddingValues),
                )
            }

            is AddBadgeUiState.Success -> Unit
        }
    }
}

@Composable
fun AddBadgeContent(
    state: AddBadgeUiState.Data,
    onNameChange: (String) -> Unit,
    onColorSelected: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onIconChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier,
) {
    val colors =
        listOf(
            "#FF6B6B" to Color(0xFFFF6B6B),
            "#0E8A8A" to Color(0xFF0E8A8A),
            "#333333" to Color(0xFF333333),
            "#F5F5F5" to Color(0xFFF5F5F5),
        )

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Badge Name", style = style_16_24_500())
        OutlinedTextField(
            value = state.name,
            onValueChange = onNameChange,
            placeholder = { Text("Enter badge name", style = style_16_24_400(), color = SoftBrown) },
            modifier = Modifier.fillMaxWidth(),
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
            shape = RoundedCornerShape(10.dp),
        )

        Text("Badge Color", style = style_16_24_500())

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            colors.forEach { (hex, color) ->
                Box(
                    modifier =
                        Modifier
                            .size(36.dp)
                            .background(color, CircleShape)
                            .border(
                                width = if (state.colorHex == hex) 3.dp else 1.dp,
                                color = if (state.colorHex == hex) Color.Black else Color.LightGray,
                                shape = CircleShape,
                            ).clickable { onColorSelected(hex) },
                )
            }
        }

        Text("Description", style = style_16_24_500())
        OutlinedTextField(
            value = state.description,
            onValueChange = onDescriptionChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(120.dp),
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
            shape = RoundedCornerShape(10.dp),
        )

        Text("Icon", style = style_16_24_500())
        OutlinedTextField(
            value = state.icon ?: "",
            onValueChange = onIconChange,
            placeholder = { Text("Select icon", style = style_16_24_400(), color = SoftBrown) },
            modifier = Modifier.fillMaxWidth(),
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
            shape = RoundedCornerShape(10.dp),
        )

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PrimaryActionButton(
                text = "Save",
                onClick = onSaveClick,
                backgroundColor = Color(0xFFFF6B6B),
                isLoading = false,
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = onCancelClick,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp),
            ) {
                Text(
                    "Cancel",
                    style = style_14_21_400(),
                    color = SoftBrown,
                )
            }
        }
    }
}

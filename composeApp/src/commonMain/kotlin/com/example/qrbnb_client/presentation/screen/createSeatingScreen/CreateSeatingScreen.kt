package com.example.qrbnb_client.presentation.screen.createSeatingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.qrbnb_client.presentation.viewmodel.CreateSeatingViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.TabBackground
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSeatingScreen(
    onBackClick: () -> Unit = {},
    onSuccess: () -> Unit = {},
    viewModel: CreateSeatingViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var selectedType by remember { mutableStateOf("room") }
    var identifier by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    LaunchedEffect(uiState.successId) {
        if (uiState.successId != null) {
            identifier = ""
            notes = ""
            selectedType = "room"

            viewModel.resetState()
            onSuccess()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Add Room / Table",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "back",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
        containerColor = Color.White,
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
                        .padding(horizontal = 16.dp),
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Create a new seating or service point",
                    style = style_16_24_400(),
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(24.dp))

                TypeSelector(
                    selectedType = selectedType,
                    onTypeSelected = { selectedType = it },
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Seating Identifier",
                    style = style_16_24_500(),
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = identifier,
                    onValueChange = { identifier = it },
                    placeholder = {
                        Text(
                            text = "Enter identifier (e.g., Room 101 / Table 4)",
                            color = SoftBrown,
                            style = style_16_24_400(),
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                    singleLine = true,
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            cursorColor = SoftBrown,
                        ),
                    shape = RoundedCornerShape(10.dp),
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Notes (Optional)",
                    style = style_16_24_500(),
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    placeholder = {
                        Text(
                            text = "",
                            color = Color.Gray,
                            fontSize = 14.sp,
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            focusedBorderColor = Color(0xFFFF6B6B),
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                        ),
                    shape = RoundedCornerShape(8.dp),
                    maxLines = 6,
                )

                if (uiState.error != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Error: ${uiState.error}",
                        color = Color.Red,
                        fontSize = 14.sp,
                    )
                }
            }

            Row(
                modifier =
                    Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Button(
                    onClick = onBackClick,
                    modifier =
                        Modifier
                            .weight(1f)
                            .height(48.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF5F5F5),
                            contentColor = Color.Black,
                        ),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }

                Button(
                    onClick = {
                        if (identifier.isNotBlank()) {
                            viewModel.createSeating(
                                type = selectedType,
                                name = identifier,
                                description = notes,
                            )
                        }
                    },
                    modifier =
                        Modifier
                            .weight(1f)
                            .height(48.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6B6B),
                            contentColor = Color.White,
                        ),
                    shape = RoundedCornerShape(24.dp),
                    enabled = identifier.isNotBlank() && !uiState.isLoading,
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp,
                        )
                    } else {
                        Text(
                            text = "Save",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TypeSelector(
    selectedType: String,
    onTypeSelected: (String) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(TabBackground, RoundedCornerShape(25.dp))
                .padding(4.dp),
    ) {
        TypeButton(
            text = "Room",
            isSelected = selectedType == "room",
            onClick = { onTypeSelected("room") },
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
        )
        TypeButton(
            text = "Table",
            isSelected = selectedType == "table",
            onClick = { onTypeSelected("table") },
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
        )
    }
}

@Composable
fun TypeButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = if (isSelected) Color.White else Color.Transparent,
                contentColor = if (isSelected) Color.Black else Color.Gray,
            ),
        elevation = if (isSelected) ButtonDefaults.buttonElevation(4.dp) else ButtonDefaults.buttonElevation(0.dp),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.height(48.dp),
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
        )
    }
}

package com.example.qrbnb_client.presentation.screen.editTagScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.EditTagUiState
import com.example.qrbnb_client.presentation.viewmodel.EditTagViewModel
import com.example.qrbnb_client.ui.style_14_21_700
import com.example.qrbnb_client.ui.style_16_24_500
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTagScreen(
    tagId: String,
    tagName: String,
    onCancel: () -> Unit,
    onDelete: () -> Unit,
    onBack: () -> Unit,
    viewModel: EditTagViewModel = koinInject(),
) {
    LaunchedEffect(Unit) {
        viewModel.initialize(tagId, tagName)
    }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is EditTagUiState.Success) {
            onBack()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Edit Tag",
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
        containerColor = Color.White,
    ) { paddingValues ->
        when (val state = uiState) {
            is EditTagUiState.Loading -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) { CircularProgressIndicator() }
            }

            is EditTagUiState.Error -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) { Text(state.message, color = Color.Red) }
            }

            is EditTagUiState.Data -> {
                EditTagContent(
                    tagName = state.tagName,
                    onTagNameChanged = { viewModel.onTagNameChanged(it) },
                    onSaveClick = { viewModel.saveTag() },
                    onDeleteClick = onDelete,
                    onCancelClick = onCancel,
                    modifier = Modifier.padding(paddingValues),
                )
            }

            is EditTagUiState.Success -> {
            }
        }
    }
}

@Composable
fun EditTagContent(
    tagName: String,
    onTagNameChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text("Tag Name", style = style_16_24_500())

        OutlinedTextField(
            value = tagName,
            onValueChange = onTagNameChanged,
            modifier = Modifier.fillMaxWidth(),
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
            shape = RoundedCornerShape(12.dp),
        )

        Button(
            onClick = onSaveClick,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B)),
            shape = RoundedCornerShape(24.dp),
        ) {
            Text("Save", color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = onCancelClick,
                shape = RoundedCornerShape(12.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF5F0F0),
                        contentColor = Color.Black,
                    ),
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier =
                    Modifier
                        .weight(1f)
                        .height(40.dp),
            ) {
                Text(
                    text = "Cancel",
                    style = style_14_21_700(),
                    color = Color.Black,
                )
            }

            Button(
                onClick = onDeleteClick,
                shape = RoundedCornerShape(12.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF5F0F0),
                        contentColor = Color.Black,
                    ),
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier =
                    Modifier
                        .weight(1f)
                        .height(40.dp),
            ) {
                Text(
                    text = "Delete",
                    style = style_14_21_700(),
                    color = Color.Black,
                )
            }
        }
    }
}

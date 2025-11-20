package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.presentation.state.AddModifierGroupUiState
import com.example.qrbnb_client.presentation.state.ModifierItemUi
import com.example.qrbnb_client.presentation.viewmodel.AddModifierGroupViewModel
import org.koin.compose.koinInject // Corrected import for koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddModifierGroupScreen(
    viewModel: AddModifierGroupViewModel = koinInject(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    var showModifierDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState is AddModifierGroupUiState.Success) {
            println("succes $uiState")
            val successState = uiState as AddModifierGroupUiState.Success
            snackbarHostState.showSnackbar(
                message = successState.message,
                duration = SnackbarDuration.Short,
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Modifier Group",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                    ),
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        when (val state = uiState) {
            is AddModifierGroupUiState.Loading -> {
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

            is AddModifierGroupUiState.Error -> {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(16.dp),
                ) {
                    Text(
                        text = state.message,
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 16.dp),
                    )
                }
            }

            is AddModifierGroupUiState.Data -> {
                AddModifierGroupContent(
                    data = state,
                    onGroupNameChanged = { viewModel.onGroupNameChanged(it) },
                    onSelectionTypeChanged = { viewModel.onSelectionTypeChanged(it) },
                    onAddModifierClick = { showModifierDialog = true },
                    onSaveClick = { viewModel.saveModifierGroup() },
                    onCancelClick = onBackClick,
                    modifier = Modifier.padding(paddingValues),
                )
            }

            is AddModifierGroupUiState.Success -> {
            }
        }
    }

    if (showModifierDialog) {
        AddModifierDialog(
            onDismiss = { showModifierDialog = false },
            onAdd = { name, price ->
                viewModel.addModifier(name, price)
                showModifierDialog = false
            },
        )
    }
}

@Composable
fun AddModifierGroupContent(
    data: AddModifierGroupUiState.Data,
    onGroupNameChanged: (String) -> Unit,
    onSelectionTypeChanged: (String) -> Unit,
    onAddModifierClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OutlinedTextField(
            value = data.groupName,
            onValueChange = onGroupNameChanged,
            placeholder = {
                Text(
                    text = "Group Name",
                    color = Color.Gray,
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
            shape = RoundedCornerShape(8.dp),
        )

        Text(
            text = "Selection Type",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )

        SelectionTypeOption(
            text = "Single Select",
            selected = data.selectionType == "Single Select",
            onClick = { onSelectionTypeChanged("Single Select") },
        )

        SelectionTypeOption(
            text = "Multiple Select",
            selected = data.selectionType == "Multiple Select",
            onClick = { onSelectionTypeChanged("Multiple Select") },
        )

        Text(
            text = "Choose how many options can be selected from this group.",
            fontSize = 13.sp,
            color = Color.Gray,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Modifiers List",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )

        data.modifiers.forEach { modifier ->
            ModifierListItem(modifier = modifier)
        }

        TextButton(
            onClick = onAddModifierClick,
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            Text(
                text = "+ Add Modifier",
                fontSize = 14.sp,
                color = Color.Black,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            TextButton(
                onClick = onCancelClick,
                modifier =
                    Modifier
                        .weight(1f)
                        .height(48.dp),
            ) {
                Text(
                    text = "Cancel",
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }

            Button(
                onClick = onSaveClick,
                modifier =
                    Modifier
                        .weight(1f)
                        .height(48.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7B7B),
                    ),
                shape = RoundedCornerShape(24.dp),
                enabled =
                    data.groupName.isNotEmpty() &&
                        data.selectionType.isNotEmpty() &&
                        data.modifiers.isNotEmpty(),
            ) {
                Text(
                    text = "Save",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun SelectionTypeOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(8.dp),
                ).clickable { onClick() }
                .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors =
                RadioButtonDefaults.colors(
                    selectedColor = Color.Black,
                    unselectedColor = Color.Gray,
                ),
        )
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black,
        )
    }
}

@Composable
fun ModifierListItem(modifier: ModifierItemUi) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5),
            ),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "Modifier Name",
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
                Text(
                    text = modifier.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Price",
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
                Text(
                    text = "$${modifier.price}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
            }

            IconButton(onClick = { /* TODO: Delete modifier */ }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Gray,
                )
            }
        }
    }
}


@Composable
fun AddModifierDialog(
    onDismiss: () -> Unit,
    onAdd: (String, String) -> Unit,
) {
    var modifierName by remember { mutableStateOf("") }
    var modifierPrice by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Add Modifier",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedTextField(
                    value = modifierName,
                    onValueChange = { modifierName = it },
                    placeholder = { Text("Modifier Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                        ),
                    shape = RoundedCornerShape(8.dp),
                )

                OutlinedTextField(
                    value = modifierPrice,
                    onValueChange = { modifierPrice = it },
                    placeholder = { Text("Price") },
                    modifier = Modifier.fillMaxWidth(),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                        ),
                    shape = RoundedCornerShape(8.dp),
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (modifierName.isNotEmpty() && modifierPrice.isNotEmpty()) {
                        onAdd(modifierName, modifierPrice)
                    }
                },
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7B7B),
                    ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.Black)
            }
        },
    )
}

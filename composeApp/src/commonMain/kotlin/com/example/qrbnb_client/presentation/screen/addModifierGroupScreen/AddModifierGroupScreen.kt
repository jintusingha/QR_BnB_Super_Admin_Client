package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.AddModifierGroupUiState
import com.example.qrbnb_client.presentation.state.ModifierItemUi
import com.example.qrbnb_client.presentation.viewmodel.AddModifierGroupViewModel
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_400_
import com.example.qrbnb_client.ui.style_16_24_700
import com.example.qrbnb_client.ui.style_18_23_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

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
            val successState = uiState as AddModifierGroupUiState.Success
            snackbarHostState.showSnackbar(
                message = successState.message,
                duration = SnackbarDuration.Short,
            )
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Add Modifier Group",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
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
                    color = SoftBrown,
                    style = style_16_24_400(),
                )
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
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

        Text(
            text = "Selection Type",
            style = style_18_23_700(),
            color = Color.Black,
        )

        SelectionTypeOption(
            text = "Single Select",
            selected = data.selectionType == "single",
            onClick = { onSelectionTypeChanged("single") },
        )

        SelectionTypeOption(
            text = "Multiple Select",
            selected = data.selectionType == "multiple",
            onClick = { onSelectionTypeChanged("multiple") },
        )

        Text(
            text = "Choose how many options can be selected from this group.",
            style = style_16_24_400_(),
            color = Black,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Modifiers List",
            style = style_18_23_700(),
            color = Black,
        )

        data.modifiers.forEach { modifier ->
            ModifierListItem(modifier = modifier)
        }

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            AddModifierPill(onClick = onAddModifierClick)
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(
                onClick = onCancelClick,
                modifier = Modifier.height(48.dp),
            ) {
                Text(
                    text = "Cancel",
                    style = style_16_24_700(),
                    color = Black,
                )
            }

            Button(
                onClick = onSaveClick,
                modifier =
                    Modifier
                        .width(84.dp)
                        .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFA5959),
                    ),
                contentPadding =
                    PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                    ),
                enabled =
                    data.groupName.isNotEmpty() &&
                            data.selectionType.isNotEmpty() &&
                            data.modifiers.isNotEmpty(),
            ) {
                Text(
                    text = "Save",
                    fontSize = 16.sp,
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
                .height(53.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFE8DCD6),
                    shape = RoundedCornerShape(12.dp),
                ).clickable { onClick() }
                .padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors =
                RadioButtonDefaults.colors(
                    selectedColor = Color.Black,
                    unselectedColor = Color(0xFFE8DCD6),
                ),
        )

        Text(
            text = text,
            fontSize = 16.sp,
            color = Color(0xFF4A3F35),
        )
    }
}

@Composable
fun ModifierListItem(modifier: ModifierItemUi, onDelete: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5),
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(modifier = Modifier.weight(1f)) {


                Text(
                    text = modifier.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.Black
                )


                Text(
                    text = "Price: $${modifier.price}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Gray
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


        containerColor = Color.White,
        titleContentColor = Color.Black,
        textContentColor = Color.Black,

        title = {
            Text(
                text = "Add Modifier",
                fontSize = 18.sp,
                color = Color.Black
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
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                )

                OutlinedTextField(
                    value = modifierPrice,
                    onValueChange = { modifierPrice = it },
                    placeholder = { Text("Price") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black
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
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7B7B),
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text("Add", color = Color.White)
            }
        },

        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.Black)
            }
        },
    )
}


@Composable
fun AddModifierPill(onClick: () -> Unit) {
    Box(
        modifier =
            Modifier
                .width(132.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF5F5F5))
                .clickable { onClick() }
                .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "+ Add Modifier",
            fontSize = 14.sp,
            color = Color.Black,
        )
    }
}

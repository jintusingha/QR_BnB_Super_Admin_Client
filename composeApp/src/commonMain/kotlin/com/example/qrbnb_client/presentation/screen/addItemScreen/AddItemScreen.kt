package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.data.remote.service.fake.*
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.AddItemFormData
import com.example.qrbnb_client.presentation.state.AddItemUiState
import com.example.qrbnb_client.presentation.state.VariantUi
import com.example.qrbnb_client.presentation.viewmodel.AddItemViewModel
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_700
import com.example.qrbnb_client.ui.style_16_20_700
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500
import com.example.qrbnb_client.ui.style_16_24_700
import com.example.qrbnb_client.ui.style_18_23_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Delete
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = koinInject(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val tags by viewModel.tags.collectAsState()
    val badges by viewModel.badges.collectAsState()
    val modifierGroups by viewModel.modifierGroups.collectAsState()

    var showSuccessDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is AddItemUiState.Success -> {
                showSuccessDialog = true
                kotlinx.coroutines.delay(100)
            }
            is AddItemUiState.Error -> {
                errorMessage = state.message
                showErrorDialog = true
            }
            else -> {}
        }
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("Success") },
            text = { Text("Item added successfully!") },
            confirmButton = {
                TextButton(onClick = {
                    showSuccessDialog = false
                }) {
                    Text("OK")
                }
            },
        )
    }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text("Error") },
            text = { Text(errorMessage) },
            confirmButton = {
                TextButton(onClick = { showErrorDialog = false }) {
                    Text("OK")
                }
            },
        )
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Add New Item",
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
    ) { padding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),
        ) {
            when (val state = uiState) {
                is AddItemUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
                is AddItemUiState.Data -> {
                    AddItemForm(
                        form = state.form,
                        categories = categories,
                        tags = tags,
                        badges = badges,
                        modifierGroups = modifierGroups,
                        viewModel = viewModel,
                    )
                }
                is AddItemUiState.Success -> {
                    AddItemForm(
                        form = AddItemFormData(),
                        categories = categories,
                        tags = tags,
                        badges = badges,
                        modifierGroups = modifierGroups,
                        viewModel = viewModel,
                    )
                }
                is AddItemUiState.Error -> {
                    AddItemForm(
                        form = AddItemFormData(),
                        categories = categories,
                        tags = tags,
                        badges = badges,
                        modifierGroups = modifierGroups,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}

@Composable
fun AddItemForm(
    form: AddItemFormData,
    categories: List<FakeCategoryDto>,
    tags: List<FakeTagDto>,
    badges: List<FakeBadgeDto>,
    modifierGroups: List<FakeModifierGroupDto>,
    viewModel: AddItemViewModel,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ImageUploadSection()

        FormField(label = "Item Name") {
            StyledOutlinedTextField(
                value = form.name,
                onValueChange = { viewModel.onNameChange(it) },
                placeholder = "Enter item name",
            )
        }

        FormField(label = "Price") {
            StyledOutlinedTextField(
                value = form.price,
                onValueChange = { viewModel.onPriceChange(it) },
                placeholder = "Enter price",
            )
        }

        FormField(label = "Description") {
            StyledOutlinedTextField(
                value = form.description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                placeholder = "Enter description",
                modifier = Modifier.height(100.dp),
                maxLines = 4,
            )
        }

        FormField(label = "Category") {
            CategoryDropdown(
                categories = categories,
                selectedId = form.categoryId,
                onSelect = { viewModel.onCategorySelect(it) },
            )
        }

        Text(
            text = "Modifiers",
            style = style_18_23_700(),
        )

        OutlinedButton(
            onClick = { /* Add modifier action */ },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
            colors =
                ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Add Modifier",
                    style = style_16_20_700(),
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Availability", style = style_16_24_400())
            Switch(
                checked = form.available,
                onCheckedChange = { viewModel.toggleAvailable() },
            )
        }

        AdvancedOptionsSection(
            badges = badges,
            tags = tags,
            modifierGroups = modifierGroups,
            selectedBadges = form.selectedBadges,
            selectedTags = form.selectedTags,
            selectedModifierGroups = form.selectedModifierGroups,
            variants = form.variants,
            onBadgesSelected = { viewModel.onBadgesSelected(it) },
            onTagsSelected = { viewModel.onTagsSelected(it) },
            onModifierGroupsSelected = { viewModel.onModifierGroupsSelected(it) },
            onAddVariant = { name, price -> viewModel.addVariant(name, price) },
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.saveItem() },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B6B),
                ),
            shape = RoundedCornerShape(25.dp),
        ) {
            Text("Save", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        TextButton(
            onClick = { /* Cancel action */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Cancel",
                style= style_16_24_700(),
                color = Black
            )
        }


        Spacer(Modifier.height(32.dp))
    }
}

@Composable
fun StyledOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier =
            modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
        placeholder = {
            Text(
                placeholder,
                style = style_16_24_400(),
                color = SoftBrown,
            )
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = singleLine,
        maxLines = if (singleLine) 1 else maxLines,
        colors =
            OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F5F5),
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
            ),
    )
}

@Composable
fun ImageUploadSection() {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .clickable { /* Image picker */ },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Upload",
                modifier = Modifier.size(48.dp),
                tint = Color.Gray,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Image Upload",
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
            Text(
                "Upload an image of the item",
                fontSize = 12.sp,
                color = Color.Gray,
            )
        }
    }
}

@Composable
fun FormField(
    label: String,
    content: @Composable () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(label, style = style_16_24_500())
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(
    categories: List<FakeCategoryDto>,
    selectedId: String,
    onSelect: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedCategory = categories.find { it.id == selectedId }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            value = selectedCategory?.name ?: "",
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(
                    "Select category",
                    style = style_16_24_400(),
                    color = Black,
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
            modifier =
                Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .heightIn(min = 56.dp),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.name) },
                    onClick = {
                        onSelect(category.id)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
fun AdvancedOptionsSection(
    badges: List<FakeBadgeDto>,
    tags: List<FakeTagDto>,
    modifierGroups: List<FakeModifierGroupDto>,
    selectedBadges: List<String>,
    selectedTags: List<String>,
    selectedModifierGroups: List<String>,
    variants: List<com.example.qrbnb_client.presentation.state.VariantUi>,
    onBadgesSelected: (List<String>) -> Unit,
    onTagsSelected: (List<String>) -> Unit,
    onModifierGroupsSelected: (List<String>) -> Unit,
    onAddVariant: (String, String?) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Advanced Options", fontWeight = FontWeight.Medium)
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) "Collapse" else "Expand",
            )
        }

        if (expanded) {
            Text(
                "Badges Assignments: Pop-ups, Variants, Modifier Groups",
                style = style_14_21_400(),
                color = SoftBrown,
            )

            ChipSelectionSection(
                title = "Badges",
                items = badges.map { it.id to it.name },
                selectedIds = selectedBadges,
                onSelectionChange = onBadgesSelected,
            )

            Spacer(modifier = Modifier.height(0.dp))

            ChipSelectionSection(
                title = "Tags",
                items = tags.map { it.id to it.name },
                selectedIds = selectedTags,
                onSelectionChange = onTagsSelected,
            )

            Spacer(modifier = Modifier.height(10.dp))

            VariantsSection(
                variants = variants,
                onAddVariant = onAddVariant,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ChipSelectionSection(
                    title = "Modifier Groups",
                    items = modifierGroups.map { it.id to it.name },
                    selectedIds = selectedModifierGroups,
                    onSelectionChange = onModifierGroupsSelected,
                )
                Spacer(modifier = Modifier.height(17.5.dp))

                CreateNewGroupChip(
                    onClick = {

                    },
                )
            }
        }
    }
}

@Composable
fun ChipSelectionSection(
    title: String,
    items: List<Pair<String, String>>,
    selectedIds: List<String>,
    onSelectionChange: (List<String>) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(
            title,
            style = style_16_24_500(),
            color = Black,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items.forEach { (id, name) ->
                val isSelected = selectedIds.contains(id)

                CustomChip(
                    text = name,
                    selected = isSelected,
                    onClick = {
                        val newSelection =
                            if (isSelected) {
                                selectedIds - id
                            } else {
                                selectedIds + id
                            }
                        onSelectionChange(newSelection)
                    },
                )
            }
        }
    }
}

@Composable
fun VariantsSection(
    variants: List<com.example.qrbnb_client.presentation.state.VariantUi>,
    onAddVariant: (String, String?) -> Unit,
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Variants", style = style_18_23_700())

        variants.forEach { variant ->
            VariantItem(variant = variant)
        }

        OutlinedButton(
            onClick = { showAddDialog = true },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
            colors =
                ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Add Variant",
                    style = style_16_20_700(),
                )
            }
        }
    }

    if (showAddDialog) {
        AddVariantDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { name, price ->
                onAddVariant(name, price)
                showAddDialog = false
            },
        )
    }
}

@Composable
fun VariantItem(variant: VariantUi) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(variant.name, fontWeight = FontWeight.Medium)
            variant.price?.let {
                Text("Price: $$it", fontSize = 12.sp, color = SoftBrown)
            }
        }
        Icon(
            painter = painterResource(Res.drawable.Delete),
            contentDescription = "Delete",
            tint = Black,
            modifier = Modifier.size(24.dp)
        )

    }
}

@Composable
fun AddVariantDialog(
    onDismiss: () -> Unit,

    onAdd: (String, String?) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFFF5F5F5),
        title = { Text("Add Variant") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            "Variant Name",
                            style = style_16_24_400(),
                            color = SoftBrown,
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            focusedBorderColor = Color(0xFFFA5959),
                            cursorColor = Color(0xFFFA5959),
                        ),
                )

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = {
                        Text(
                            "Price (Optional)",
                            style = style_16_24_400(),
                            color = SoftBrown,
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .heightIn(min = 56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            focusedBorderColor = Color(0xFFFA5959),
                            cursorColor = Color(0xFFFA5959),
                        ),
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (name.isNotBlank()) {
                        onAdd(name, price.ifBlank { null })
                    }
                },
            ) {
                Text("Add", color = Black)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Black)
            }
        },
    )
}

@Composable
fun CustomChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (selected) SoftBrown.copy(alpha = 0.15f) else Color(0xFFF5F5F5),
                ).clickable { onClick() }
                .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = style_14_21_700(),
            color = if (selected) SoftBrown else Black,
        )
    }
}

@Composable
fun CreateNewGroupChip(onClick: () -> Unit) {
    Box(
        modifier =
            Modifier
                .height(44.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF5F5F5))
                .clickable { onClick() }
                .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "+ Create New Group",
            style = style_14_21_700(),
            color = Black,
        )
    }
}

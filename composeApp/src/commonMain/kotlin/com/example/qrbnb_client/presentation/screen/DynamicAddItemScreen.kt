package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.qrbnb_client.data.ImagePickerHelper
import com.example.qrbnb_client.domain.entity.AddItemsResponse.DynamicFormEntity
import com.example.qrbnb_client.domain.entity.AddItemsResponse.FieldOptionEntity
import com.example.qrbnb_client.domain.entity.AddItemsResponse.FormFieldEntity
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.viewmodel.AddItemFormViewModel
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_16_20_700
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500
import com.example.qrbnb_client.ui.style_18_23_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicAddItemScreen(
    viewModel: AddItemFormViewModel = koinInject(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState

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
            Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

                uiState.error != null ->
                    Text(
                        "Error: ${uiState.error}",
                        Modifier.align(Alignment.Center),
                        color = Color.Red,
                    )

                uiState.form != null ->
                    DynamicFormUI(
                        form = uiState.form!!,
                        onFieldChange = viewModel::onFieldValueChanged,
                        onSubmit = viewModel::submitForm,
                    )
            }
        }
    }
}

@Composable
fun DynamicFormUI(
    form: DynamicFormEntity,
    onFieldChange: (String, Any?) -> Unit,
    onSubmit: () -> Unit,
) {
    var bottomSheetData by remember { mutableStateOf<BottomSheetData?>(null) }
    var isAdvancedOptionsExpanded by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        form.fields.forEach { field ->

            when (field.type.lowercase()) {
                "text" ->
                    LabeledStyledTextField(
                        label = field.label,
                        value = field.value as? String ?: "",
                        onValueChange = { onFieldChange(field.id, it) },
                    )

                "number" ->
                    LabeledStyledTextField(
                        label = field.label,
                        value = field.value as? String ?: "",
                        onValueChange = { onFieldChange(field.id, it) },
                    )

                "textarea" ->
                    LabeledLargeTextArea(
                        label = field.label,
                        value = field.value as? String ?: "",
                        onValueChange = { onFieldChange(field.id, it) },
                    )

                "switch" -> {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(field.label, style = style_16_24_400(), color = Black)
                        Switch(
                            checked = field.value as? Boolean ?: field.defaultValue ?: false,
                            onCheckedChange = { onFieldChange(field.id, it) },
                        )
                    }

                    if (field.id == "availability") {
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .clickable { isAdvancedOptionsExpanded = !isAdvancedOptionsExpanded }
                                    .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                "Advanced Options",
                                style = style_16_24_500(),
                                color = Black,
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Black,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }
                }

                "file" -> FilePickerField(
                    fieldId = field.id,
                    label = field.label,
                    subtitle = "Upload an image of the item",
                    onPick = { localPath -> onFieldChange(field.id, localPath) }
                )


                "select", "multiselect", "dynamiclist" -> {
                    val shouldShow =
                        field.id == "category" ||
                            (
                                isAdvancedOptionsExpanded && (
                                    field.type == "select" ||
                                        field.type == "multiselect" ||
                                        field.type == "dynamicList"
                                )
                            )

                    if (shouldShow) {
                        SelectField(
                            label = field.label,
                            selectedText = field.selectedDisplayText(),
                            onClick = { bottomSheetData = BottomSheetData(field) },
                        )

                        if (field.id == "category") {
                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Modifiers",
                                style = style_18_23_700(),
                                color = Black,
                            )

                            OutlinedButton(
                                onClick = { /* TODO: Add Modifier Action */ },
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .height(56.dp),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
                                colors =
                                    ButtonDefaults.outlinedButtonColors(
                                        containerColor = Color.White,
                                        contentColor = Black,
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
                        }

                        if (isAdvancedOptionsExpanded &&
                            (field.id == "variants" || field.label.contains("variant", ignoreCase = true))
                        ) {
                            OutlinedButton(
                                onClick = { /* TODO: Add Variant Action */ },
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .height(56.dp),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
                                colors =
                                    ButtonDefaults.outlinedButtonColors(
                                        containerColor = Color.White,
                                        contentColor = Black,
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
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onSubmit,
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
            Text("Save", fontSize = 16.sp)
        }

        Spacer(Modifier.height(32.dp))
    }

    bottomSheetData?.let { data ->
        BottomSheetComponent(
            field = data.field,
            onDismiss = { bottomSheetData = null },
            onSelect = { value -> onFieldChange(data.field.id, value) },
        )
    }
}

data class BottomSheetData(
    val field: FormFieldEntity,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetComponent(
    field: FormFieldEntity,
    onDismiss: () -> Unit,
    onSelect: (Any?) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Text(field.label, style = style_18_23_700(), color = Black)
            Spacer(Modifier.height(16.dp))

            if (field.type.equals("select", true) &&
                field.options != null &&
                field.options!!.isNotEmpty()
            ) {
                field.options!!.forEach { option ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelect(option.value)
                                onDismiss()
                            }.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(option.label, style = style_16_24_400(), color = Black)
                    }
                }
                return@ModalBottomSheet
            }

            if (field.type.equals("multiselect", true)) {
                val selected = (field.value as? List<String>) ?: emptyList()
                var mutableSelected by remember { mutableStateOf(selected.toMutableList()) }

                field.options?.forEach { opt ->
                    val isChecked = mutableSelected.contains(opt.value)

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                mutableSelected =
                                    mutableSelected.toMutableList().apply {
                                        if (isChecked) remove(opt.value) else add(opt.value)
                                    }
                                onSelect(mutableSelected)
                            }.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(opt.label, style = style_16_24_400(), color = Black)
                        Checkbox(checked = isChecked, onCheckedChange = null)
                    }
                }

                return@ModalBottomSheet
            }

            if (field.type.equals("dynamiclist", true)) {
                field.options?.forEach { group ->

                    Text(group.label, style = style_16_24_500(), color = Black)
                    Spacer(Modifier.height(8.dp))

                    group.options?.forEach { opt ->

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSelect(opt.value)
                                    onDismiss()
                                }.padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(opt.label, style = style_16_24_400(), color = Black)
                            opt.price?.let {
                                Text("+₹$it", style = style_16_24_400(), color = SoftBrown)
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))
                }

                return@ModalBottomSheet
            }

            if (field.id == "modifierGroups") {
                field.options?.forEach { group ->

                    Text(group.label, style = style_16_24_500(), color = Black)
                    Spacer(Modifier.height(8.dp))

                    group.options?.forEach { opt ->

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable { onSelect(opt.value) }
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(opt.label, style = style_16_24_400(), color = Black)
                            opt.price?.let {
                                Text("+₹$it", style = style_16_24_400(), color = SoftBrown)
                            }
                        }
                    }

                    Spacer(Modifier.height(20.dp))
                }

                return@ModalBottomSheet
            }

            Text("No options available", color = SoftBrown)
        }
    }
}

@Composable
fun SelectField(
    label: String,
    selectedText: String,
    onClick: () -> Unit,
) {
    Column {
        Text(label, style = style_16_24_500(), color = Black)
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                .clickable { onClick() }
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                selectedText.ifBlank { "Select $label" },
                style = style_16_24_400(),
                color = if (selectedText.isBlank()) SoftBrown else Black,
            )
        }
    }
}

@Composable
fun FilePickerField(
    fieldId: String,
    label: String,
    subtitle: String = "Upload an image of the item",
    onPick: (String) -> Unit,
    viewModel: AddItemFormViewModel = koinInject(),
    imagePickerHelper: ImagePickerHelper = koinInject(),
) {
    var selectedLocalUri by remember { mutableStateOf<String?>(null) }
    var isUploading by remember { mutableStateOf(false) }

    val pickImage = imagePickerHelper.rememberImagePicker { uri ->
        selectedLocalUri = uri
        onPick(uri)
        isUploading = true
        viewModel.uploadImage(fieldId, uri)
    }


    val fieldValue = viewModel.uiState.value.form
        ?.fields
        ?.find { it.id == fieldId }
        ?.value as? String


    LaunchedEffect(fieldValue) {
        if (fieldValue != null && fieldValue.startsWith("http")) {
            isUploading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
            .clickable { pickImage() },
        contentAlignment = Alignment.Center
    ) {

        when {
            isUploading -> {
                CircularProgressIndicator()
            }


            fieldValue != null && fieldValue.startsWith("http") -> {
                Image(
                    painter = rememberAsyncImagePainter(fieldValue),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }


            selectedLocalUri != null -> {
                Image(
                    painter = rememberAsyncImagePainter(selectedLocalUri),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            else -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(label, style = style_16_24_500(), color = Black)
                    Text(subtitle, style = style_16_24_400(), color = SoftBrown)
                }
            }
        }
    }
}

@Composable
fun LabeledStyledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = label, style = style_16_24_500(), color = Black)
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(label, style = style_16_24_400(), color = SoftBrown) },
            shape = RoundedCornerShape(12.dp),
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
        )
    }
}

@Composable
fun LabeledLargeTextArea(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Text(text = label, style = style_16_24_500(), color = Black)
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(140.dp),
            placeholder = { Text(label, style = style_16_24_400(), color = SoftBrown) },
            shape = RoundedCornerShape(12.dp),
            singleLine = false,
            maxLines = 10,
            colors =
                OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                ),
        )
    }
}

fun FormFieldEntity.selectedDisplayText(): String =
    when (type.lowercase()) {
        "select" ->
            options?.find { it.value == value }?.label ?: ""

        "multiselect" ->
            (value as? List<String>)
                ?.map { v -> options?.find { it.value == v }?.label ?: v }
                ?.joinToString(", ")
                ?: ""

        "dynamiclist" -> {
            val selectedValue = value?.toString()
            if (selectedValue != null && options != null) {
                options!!.forEach { group ->
                    group.options?.forEach { option ->
                        if (option.value == selectedValue) {
                            return option.label
                        }
                    }
                }
            }
            selectedValue ?: ""
        }

        else -> ""
    }

package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.domain.entity.AddItemsResponse.DynamicFormEntity
import com.example.qrbnb_client.domain.entity.AddItemsResponse.FormFieldEntity
import com.example.qrbnb_client.presentation.viewmodel.AddItemFormViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicAddItemScreen(
    viewModel: AddItemFormViewModel = koinInject(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add New Item") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            when {
                uiState.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                uiState.error != null -> Text("Error: ${uiState.error}", Modifier.align(Alignment.Center))
                uiState.form != null -> DynamicFormUI(
                    form = uiState.form!!,
                    onFieldChange = viewModel::onFieldValueChanged,
                    onSubmit = viewModel::submitForm
                )
            }
        }
    }
}

@Composable
fun DynamicFormUI(
    form: DynamicFormEntity,
    onFieldChange: (String, Any?) -> Unit,
    onSubmit: () -> Unit
) {
    var bottomSheetData by remember { mutableStateOf<BottomSheetData?>(null) }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        form.fields.forEach { field ->

            when (field.type.lowercase()) {

                "text" -> {
                    OutlinedTextField(
                        value = field.value as? String ?: "",
                        onValueChange = { onFieldChange(field.id, it) },
                        placeholder = { Text(field.label) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                "number" -> {
                    OutlinedTextField(
                        value = field.value as? String ?: "",
                        onValueChange = { onFieldChange(field.id, it) },
                        placeholder = { Text(field.label) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                "textarea" -> {
                    OutlinedTextField(
                        value = field.value as? String ?: "",
                        onValueChange = { onFieldChange(field.id, it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        placeholder = { Text(field.label) },
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                "switch" -> {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(field.label)
                        Switch(
                            checked = field.value as? Boolean ?: field.defaultValue ?: false,
                            onCheckedChange = { onFieldChange(field.id, it) }
                        )
                    }
                }

                "file" -> {
                    FilePickerField(
                        label = field.label,
                        onPick = { onFieldChange(field.id, "dummy-file-path") }
                    )
                }

                "select", "multiselect", "dynamiclist" -> {
                    SelectField(
                        label = field.label,
                        selectedText = field.selectedDisplayText(),
                        onClick = { bottomSheetData = BottomSheetData(field) }
                    )
                }
            }
        }

        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Save", fontSize = 16.sp)
        }

        Spacer(Modifier.height(30.dp))
    }

    bottomSheetData?.let { data ->
        BottomSheetComponent(
            field = data.field,
            onDismiss = { bottomSheetData = null },
            onSelect = { value -> onFieldChange(data.field.id, value) }
        )
    }
}

data class BottomSheetData(val field: FormFieldEntity)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetComponent(
    field: FormFieldEntity,
    onDismiss: () -> Unit,
    onSelect: (Any?) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(field.label, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(12.dp))


            if (field.type.equals("dynamiclist", ignoreCase = true)) {

                field.fields?.forEach { subField ->

                    when (subField.type.lowercase()) {

                        "text" -> {
                            OutlinedTextField(
                                value = subField.value as? String ?: "",
                                onValueChange = { onSelect(it) },
                                placeholder = { Text(subField.label) },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        "number" -> {
                            OutlinedTextField(
                                value = subField.value?.toString() ?: "",
                                onValueChange = { onSelect(it) },
                                placeholder = { Text(subField.label) },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))
                }

                Button(
                    onClick = { onSelect("ADD_DYNAMIC_ITEM") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Variant")
                }

                return@ModalBottomSheet
            }


            if (field.optionsEndpoint != null) {
                Text("Options will load from ${field.optionsEndpoint}")
            } else {
                Text("No options available")
            }
        }
    }
}


@Composable
fun SelectField(label: String, selectedText: String, onClick: () -> Unit) {
    Column {
        Text(label)
        Box(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                .clickable { onClick() }
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(selectedText.ifBlank { "Select $label" })
        }
    }
}

@Composable
fun FilePickerField(label: String, onPick: () -> Unit) {
    Column {
        Text(label)
        Box(
            Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                .clickable { onPick() },
            contentAlignment = Alignment.Center
        ) {
            Text("Upload Image")
        }
    }
}

fun FormFieldEntity.selectedDisplayText(): String {
    return when (type.lowercase()) {
        "select", "multiselect", "dynamiclist" ->
            if (optionsEndpoint != null) "Select from list" else "No options"
        else -> ""
    }
}

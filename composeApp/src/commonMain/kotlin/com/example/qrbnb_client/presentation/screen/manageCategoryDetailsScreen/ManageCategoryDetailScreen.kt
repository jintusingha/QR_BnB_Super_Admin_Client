package com.example.qrbnb_client.presentation.screen.manageCategoryDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.qrbnb_client.domain.entity.manageCategoryDetailsResponse.ManageCategoryDetails
import com.example.qrbnb_client.navigation.ScreenRoute
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.ManageCategoryDetailsUiState
import com.example.qrbnb_client.presentation.viewmodel.ManageCategoryDetailViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.body16Regular
import com.example.qrbnb_client.ui.search_field_color
import com.example.qrbnb_client.ui.search_icon_placeholdertext
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_700
import com.example.qrbnb_client.ui.style_16_24_500
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.arrowdown
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon
import qr_bnb_client.composeapp.generated.resources.searchicon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCategoryDetailScreen(

    categoryId: String,
    categoryName: String = "Manage Category",
    viewModel: ManageCategoryDetailViewModel = koinInject(),
    onNavigateBack: () -> Unit = {},
    onAddItemClick: () -> Unit = {},
    onEditClick: (String) -> Unit = {},
    onDeleteClick: (String) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Available") }

    LaunchedEffect(categoryId) {
        viewModel.loadManageCategoryDetails()
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomTopAppBar(title = "Manage Category", navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        painterResource(Res.drawable.leftArrowIcon),
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp),
                    )
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddItemClick
                ,
                containerColor = Color(0xFFF75C5C),
                contentColor = Color.White,
                shape = RoundedCornerShape(28.dp),
                modifier =
                    Modifier
                        .width(64.dp)
                        .height(56.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item",
                    modifier = Modifier.size(24.dp),
                )
            }
        },
    ) { paddingValues ->

        when (uiState) {
            is ManageCategoryDetailsUiState.Loading -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Loading Items...")
                    }
                }
            }

            is ManageCategoryDetailsUiState.Error -> {
                val message = (uiState as ManageCategoryDetailsUiState.Error).message
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Error: $message", color = Color.Red)
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(onClick = { viewModel.loadManageCategoryDetails() }) {
                            Text("Retry")
                        }
                    }
                }
            }

            is ManageCategoryDetailsUiState.Success -> {
                val items = (uiState as ManageCategoryDetailsUiState.Success).categories

                val filteredItems =
                    items.filter { item ->
                        val matchesSearch = item.name.contains(searchQuery, ignoreCase = true)
                        val matchesFilter =
                            when (selectedFilter) {
                                "Available" -> item.isAvailable
                                "Unavailable" -> !item.isAvailable
                                else -> true
                            }
                        matchesSearch && matchesFilter
                    }

                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(Res.drawable.searchicon),
                                contentDescription = "Search",
                                modifier = Modifier.size(24.dp),
                                tint = search_icon_placeholdertext,
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Search items",
                                style = body16Regular(),
                                color = search_icon_placeholdertext,
                            )
                        },
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        colors =
                            OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = search_field_color,
                                focusedContainerColor = search_field_color,
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                            ),
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    FilterChip(
                        selectedFilter = selectedFilter,
                        onFilterSelected = { selectedFilter = it },
                    )

                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 90.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        items(filteredItems) { item ->
                            CategoryItemCard(
                                item = item,
                                onEditClick = { onEditClick(item.id) },
                                onDeleteClick = { onDeleteClick(item.id) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterChip(
    selectedFilter: String,
    onFilterSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .padding(start = 16.dp, bottom = 8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F0F0))
                    .clickable { expanded = true }
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = selectedFilter,
                fontSize = 14.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                painter = painterResource(Res.drawable.arrowdown),
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color.Black,
            )
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            listOf("Available", "Unavailable", "All").forEach { filter ->
                DropdownMenuItem(
                    text = { Text(filter) },
                    onClick = {
                        onFilterSelected(filter)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
fun CategoryItemCard(
    item: ManageCategoryDetails,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(72.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(item.image),
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp)),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = item.name,
                style = style_16_24_500(),
            )

            Text(
                text = "$${item.price}",
                style = style_14_21_400(),
                color = SoftBrown,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "Edit",
                    style = style_14_21_400(),
                    color = SoftBrown,
                    modifier = Modifier.clickable { onEditClick() },
                )

                Text(
                    text = "|",
                    style = style_14_21_400(),
                    color = SoftBrown,
                    modifier = Modifier.padding(horizontal = 2.dp),
                )

                Text(
                    text = "Delete",
                    style = style_14_21_400(),
                    color = SoftBrown,
                    modifier = Modifier.clickable { onDeleteClick() },
                )
            }
        }

        Switch(
            checked = item.isAvailable,
            onCheckedChange = { /* Handle toggle */ },
            colors =
                SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF4CAF50),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFFEEEEEE),
                    uncheckedBorderColor = Color.Transparent,
                    uncheckedIconColor = Color.White,
                    disabledUncheckedThumbColor = Color.White,
                    disabledUncheckedTrackColor = Color(0xFFEEEEEE),
                ),
        )
    }
}

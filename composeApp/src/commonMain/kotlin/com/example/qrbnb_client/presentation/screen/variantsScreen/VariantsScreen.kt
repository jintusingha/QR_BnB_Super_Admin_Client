package com.example.qrbnb_client.presentation.screen.variantsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.domain.entity.modifierGroup.ModifierGroupResponseEntity
import com.example.qrbnb_client.domain.entity.variantResponse.VariantsEntity
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.ModifierGroupsUiState
import com.example.qrbnb_client.presentation.state.VariantsUiState
import com.example.qrbnb_client.presentation.viewmodel.ModifierGroupsViewModel
import com.example.qrbnb_client.presentation.viewmodel.VariantsViewModel
import com.example.qrbnb_client.ui.RosyBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_400_
import com.example.qrbnb_client.ui.style_16_24_500
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.edit
import qr_bnb_client.composeapp.generated.resources.hamburger
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VariantsScreen(
    onBackClick: () -> Unit = {},
    onAddGroupClick: () -> Unit = {},
    onEditGroupClick: (String) -> Unit = {},
    viewModel: VariantsViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Variants",
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
        floatingActionButton = {
            AddModifierFloatingButton(onAddGroupClick)
        },
        containerColor = Color.White,
    ) { paddingValues ->

        when (val state = uiState) {
            is VariantsUiState.Loading -> {
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

            is VariantsUiState.Error -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = state.message, color = Color.Red)
                }
            }

            is VariantsUiState.Success -> {
                VariantsList(
                    items = state.items,
                    paddingValues = paddingValues,
                    onEditClick = { id -> onEditGroupClick(id) },
                )
            }
        }
    }
}

@Composable
fun VariantsList(
    items: List<VariantsEntity>,
    paddingValues: PaddingValues,
    onEditClick: (String) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
                .padding(paddingValues),
    ) {
        Text(
            "Manage Variations of your menu item",
            style = style_16_24_400_(),
        )
        Spacer(Modifier.height(13.5.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            items(items) { group ->
                VariantsItem(
                    item = group,
                    onEditClick = { onEditClick(group.id) },
                )
            }
        }
    }
}

@Composable
fun VariantsItem(
    item: VariantsEntity,
    onEditClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onEditClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = item.name,
                style = style_16_24_500(),
            )

            Text(
                text = "${item.itemCount} items",
                style = style_14_21_400(),
                color = RosyBrown,
            )
        }

        IconButton(onClick = onEditClick) {
            Icon(
                painter = painterResource(Res.drawable.edit),
                modifier = Modifier.size(24.dp),
                contentDescription = "edit",
            )
        }
    }
}

@Composable
fun AddModifierFloatingButton(onAddClick: () -> Unit) {
    FloatingActionButton(
        onClick = onAddClick,
        containerColor = Color(0xFFF75C5C),
        contentColor = Color.White,
        shape = RoundedCornerShape(28.dp),
        modifier =
            Modifier
                .width(64.dp)
                .height(56.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Modifier Group",
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

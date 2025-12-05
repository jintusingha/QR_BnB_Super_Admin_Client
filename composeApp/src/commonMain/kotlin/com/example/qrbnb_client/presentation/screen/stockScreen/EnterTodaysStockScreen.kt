package com.example.qrbnb_client.presentation.screen.stockScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.qrbnb_client.data.remote.model.stockItemDto.StockCategory
import com.example.qrbnb_client.domain.entity.stockItemEntity.StockItemEntity
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.viewmodel.StockViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@Composable
fun EnterTodaysStockScreen(
    onBackClick: () -> Unit,
    viewModel: StockViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val showSave by viewModel.isSaveButtonVisible.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Enter Today's Stock",
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
        bottomBar = {
            AnimatedVisibility(
                visible = showSave,
                enter = slideInVertically { it },
                exit = slideOutVertically { it },
            ) {
                Surface(
                    shadowElevation = 8.dp,
                    color = Color.White,
                ) {
                    BottomButtons(
                        onCancel = onBackClick,
                        onSave = {
                            println(" Save button clicked!")
                        },
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        },
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
        ) {
            Text(
                text = "Update your inventory for today's operations.",
                style = style_14_21_400(),
                color = SoftBrown,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )

            CategoryFilters(
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = { viewModel.onCategorySelected(it) },
            )

            Spacer(modifier = Modifier.height(8.dp))

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Error: ${uiState.error}",
                            color = Color.Red,
                            fontSize = 16.sp,
                        )
                    }
                }

                else -> {
                    StockItemsList(
                        items = uiState.items,
                        onQuantityChange = { itemId, newQty ->
                            viewModel.updateItemQuantity(itemId, newQty)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryFilters(
    selectedCategory: StockCategory?,
    onCategorySelected: (StockCategory?) -> Unit,
) {
    val categories =
        listOf(
            null to "All",
            StockCategory.FOOD to "Food",
            StockCategory.BEVERAGES to "Beverages",
            StockCategory.SUPPLIES to "Supplies",
        )

    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(categories) { (category, label) ->

            val isSelected = selectedCategory == category

            Text(
                text = label,
                style = style_14_21_700(),
                color = if (isSelected) Color.Black else SoftBrown,
                modifier =
                    Modifier.clickable {
                        onCategorySelected(category)
                    },
            )
        }
    }
}

@Composable
private fun StockItemsList(
    items: List<StockItemEntity>,
    onQuantityChange: (Int, Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(items, key = { it.id }) { item ->
            StockItemRow(
                item = item,
                onQuantityChange = { newQty ->

                    onQuantityChange(item.id, newQty)
                },
            )
        }
    }
}

@Composable
private fun StockItemRow(
    item: StockItemEntity,
    onQuantityChange: (Int) -> Unit,
) {
    var qtyText by remember(item.id, item.quantity) {
        mutableStateOf(item.quantity.toString())
    }

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.name,
            modifier =
                Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(item.name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text("$${item.price}", fontSize = 14.sp, color = Color(0xFF757575))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                value = qtyText,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        qtyText = input
                        onQuantityChange(input.toIntOrNull() ?: 0)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(IntrinsicSize.Min),
                singleLine = true,
                textStyle =
                    LocalTextStyle.current.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    ),
                cursorBrush = SolidColor(Color.Black),
            )

            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = item.unit,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        }
    }
}

@Composable
private fun BottomButtons(
    onCancel: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // LEFT BUTTON (Cancel)
        OutlinedButton(
            onClick = onCancel,
            modifier =
                Modifier
                    .height(40.dp)
                    .defaultMinSize(minWidth = 84.dp),
            shape = RoundedCornerShape(20.dp),
            colors =
                ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFFF5F0F0),
                    contentColor = Color.Black,
                ),
            border =
                ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.dp,
                    brush = SolidColor(Color(0xFFE0E0E0)),
                ),
        ) {
            Text(
                "Cancel",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Button(
            onClick = onSave,
            modifier =
                Modifier
                    .height(40.dp)
                    .defaultMinSize(minWidth = 84.dp),
            shape = RoundedCornerShape(20.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B6B),
                    contentColor = Color.White,
                ),
        ) {
            Text(
                "Save Stock",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = style_14_21_700(),
            )
        }
    }
}

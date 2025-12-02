package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil3.compose.AsyncImage
import com.example.qrbnb_client.domain.entity.menuEntity.MenuCategoryEntity
import com.example.qrbnb_client.domain.entity.menuEntity.MenuEntity
import com.example.qrbnb_client.domain.entity.menuEntity.MenuItemEntity
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.MenuClientUiState
import com.example.qrbnb_client.presentation.utility.CartStore
import com.example.qrbnb_client.presentation.viewmodel.MenuViewModel
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_700
import com.example.qrbnb_client.ui.style_16_20_700
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_400_
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon
import qr_bnb_client.composeapp.generated.resources.searchicon

@Composable
fun ManualOrderScreen(
    viewModel: MenuViewModel= koinInject(),
    onCloseClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        CartStore.seatingId = "TEST-SEATING-ID-001"// FAKE SEATING ID FOR NOW WILL REPLACE WEHEN CONNECTING NAVIGATION
        viewModel.loadMenu()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "New Manual Order",
                navigationIcon = {
                    IconButton(onClick = onCloseClick) {
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
        when (val state = uiState) {
            is MenuClientUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is MenuClientUiState.Success -> {
                MenuContent(
                    menu = state.menu,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            is MenuClientUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun MenuContent(
    menu: MenuEntity,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf(menu.categories.firstOrNull()?.id ?: "") }
    var searchQuery by remember { mutableStateOf("") }
    val itemQuantities = remember { mutableStateMapOf<String, Int>() }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )


        CategoryTabs(
            categories = menu.categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )


        val filteredItems = menu.items.filter { item ->
            item.categoryId == selectedCategory &&
                    (searchQuery.isEmpty() || item.name.contains(searchQuery, ignoreCase = true))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredItems) { item ->
                MenuItem(
                    item = item,
                    quantity = itemQuantities[item.id] ?: 1,
                    onQuantityChange = { newQuantity ->


                        itemQuantities[item.id] = newQuantity


                        CartStore.updateItem(item.id, newQuantity)
                    }
                )
            }
        }

    }
}

@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.searchicon),
                contentDescription = "Search",
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF9E9E9E),
            )
        },
        placeholder = {
            Text(
                "Search Orders",
                style = LocalTextStyle.current.copy(fontSize = 16.sp),
                color = Color(0xFF9E9E9E),
                modifier = Modifier.padding(bottom = 2.dp),
            )
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF5F0F0),
            focusedContainerColor = Color(0xFFF5F0F0),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
        )
    )
}

@Composable
private fun CategoryTabs(
    categories: List<MenuCategoryEntity>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            CategoryTab(
                category = category,
                isSelected = category.id == selectedCategory,
                onClick = { onCategorySelected(category.id) }
            )
        }
    }
}

@Composable
private fun CategoryTab(
    category: MenuCategoryEntity,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = category.name,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
            .background(if (isSelected) Color.Transparent else Color.Transparent)
            .padding(bottom = 4.dp)
            .then(
                if (isSelected) {
                    Modifier.drawWithContent {
                        drawContent()
                        drawLine(
                            color = Color.Gray,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                } else Modifier
            ),
        color = if (isSelected) Color.Black else SoftBrown,
        style= style_14_21_700()
    )
}

@Composable
private fun MenuItem(
    item: MenuItemEntity,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.categoryId.take(n=10),
                    style= style_14_21_400(),
                    color=SoftBrown
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.name,
                    color = Color.Black,
                    style= style_16_20_700()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$${String.format("%.2f", item.price)}",
                    style=style_14_21_400(),
                    color=SoftBrown
                )
            }

            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier
                    .size(120.dp, 80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Quantity Controls
        QuantityControl(
            quantity = quantity,
            onQuantityChange = onQuantityChange
        )
    }
}

@Composable
private fun QuantityControl(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Quantity",
            style= style_16_24_400_(),
            color= Black,
            modifier = Modifier.weight(1f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(
                onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
                modifier = Modifier.size(32.dp)
            ) {
                Text(
                    text = "−",
                    fontSize = 20.sp,
                    color = Color(0xFF757575)
                )
            }

            Text(
                text = quantity.toString(),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )

            IconButton(
                onClick = { onQuantityChange(quantity + 1) },
                modifier = Modifier.size(32.dp)
            ) {
                Text(
                    text = "+",
                    fontSize = 20.sp,
                    color = Color(0xFF757575)
                )
            }
        }
    }
}
package com.example.qrbnb_client.presentation.screen.ordersListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.qrbnb_client.domain.entity.orderListResponse.OrderListItem
import com.example.qrbnb_client.domain.entity.orderListResponse.OrderListSummary
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.OrdersUiState
import com.example.qrbnb_client.presentation.viewmodel.OrderListViewModel
import com.example.qrbnb_client.ui.Black_color
import com.example.qrbnb_client.ui.body16Regular
import com.example.qrbnb_client.ui.search_icon_placeholdertext
import com.example.qrbnb_client.ui.soft_reddish
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_500
import com.example.qrbnb_client.ui.style_14_21_700
import com.example.qrbnb_client.ui.style_16_20_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.filter_image
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon
import qr_bnb_client.composeapp.generated.resources.searchicon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersListScreen(
    clientId: String,
    onBackClick: () -> Unit,
    viewModel: OrderListViewModel = koinInject(),
    onOrderClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var selectedTab by remember { mutableStateOf("All") }
    LaunchedEffect(selectedTab) {
        if (selectedTab == "All") {
            viewModel.loadOrders(clientId)
        } else {
            viewModel.loadOrders(clientId, selectedTab)
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomTopAppBar(
                title = "Orders",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "LeftArrow",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.filter_image),
                            contentDescription = "Orders",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Box(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(paddingValues),
//                    .background(Color(0xFFF5F5F5)),
        ) {
            when (val currentState = state) {
                is OrdersUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
                is OrdersUiState.Error -> {
                    Column(
                        modifier =
                            Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Error",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = currentState.message,
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.loadOrders(clientId) }) {
                            Text("Retry")
                        }
                    }
                }
                is OrdersUiState.Success -> {
                    OrdersContent(
                        summary = currentState.ordersRes.summary,
                        orders = currentState.ordersRes.orders,
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it },
                        onOrderClick = { orderId ->
                            onOrderClick(orderId)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun OrdersContent(
    summary: OrderListSummary,
    orders: List<OrderListItem>,
    selectedTab: String,
    onOrderClick: (String) -> Unit,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize().padding(top = 8.dp)) {
        Column(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp),
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
                        "Search Orders",
                        style = body16Regular(),
                        color = search_icon_placeholdertext,
                        modifier = Modifier.padding(bottom = 2.dp),
                    )
                },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors =
                    OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF5F0F0),
                        focusedContainerColor = Color(0xFFF5F0F0),
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
            )
        }
        Spacer(Modifier.height(12.dp))

        StatusTabs(summary = summary, selectedTab = selectedTab, onTabSelected = onTabSelected)
        Spacer(Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(orders) { order ->
                OrderCard(
                    order = order,
                    onClick = { orderId ->
                        onOrderClick(orderId)
                    },
                )
            }
        }
    }
}

@Composable
fun StatusTabs(
    summary: OrderListSummary,
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val tabs =
        listOf(
            "All" to (summary.new + summary.preparing + summary.ready + summary.delivered),
            "New" to summary.new,
            "Preparing" to summary.preparing,
            "Ready" to summary.ready,
            "Delivered" to summary.delivered,
        )

    val selectedTabIndex = tabs.indexOfFirst { it.first == selectedTab }.coerceAtLeast(0)

    val indicatorColor = Color(0xFF9E474A)

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier.fillMaxWidth(),
        edgePadding = 16.dp,
        divider = {},
        containerColor = Color.Transparent,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = indicatorColor,
                height = 2.dp,
            )
        },
    ) {
        tabs.forEach { (label, count) ->
            val selected = selectedTab == label

            val textColor = if (selected) Color(0xFF1C0D0D) else indicatorColor

            Tab(
                selected = selected,
                onClick = { onTabSelected(label) },
                text = {
                    Text(
                        text = "$label ($count)",
                        style = style_14_21_700(),
                        color = textColor,
                    )
                },
            )
        }
    }
}

@Composable
fun OrderCard(
    order: OrderListItem,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .clickable { onClick(order.orderId) },
        shape = RoundedCornerShape(12.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = "Order #${order.orderId}",
                    style = style_14_21_400(),
                    color = soft_reddish,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${order.timeAgo} • ${order.seatingArea?.name ?: "Unknown"}",
                    style = style_16_20_700(),
                    color = Black_color,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Guest: ${order.guest.name} • ${order.items.summary.take(n = 5)}",
                    style = style_14_21_400(),
                    color = soft_reddish,
                )
                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { /* Handle accept */ },
                    shape = RoundedCornerShape(12.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFCE4EC),
                            contentColor = Black_color,
                        ),
                    modifier =
                        Modifier
                            .width(96.dp)
                            .height(32.dp)
                            .graphicsLayer(
                                rotationZ = 0f,
                                alpha = 1f,
                            ),
                    contentPadding = PaddingValues(start = 16.dp, end = 8.dp, top = 0.dp, bottom = 0.dp),
                ) {
                    Text(
                        text = "Accept",
                        style = style_14_21_500(),
                        color = Black_color,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Black_color,
                        modifier = Modifier.size(16.dp),
                    )
                }
            }

            AsyncImage(
                model = order.thumbnail,
                contentDescription = "Order image",
                modifier =
                    Modifier
                        .width(130.dp)
                        .height(130.dp)
                        .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

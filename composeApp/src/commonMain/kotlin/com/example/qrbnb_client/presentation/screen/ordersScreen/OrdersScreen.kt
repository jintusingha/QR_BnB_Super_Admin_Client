package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.qrbnb_client.domain.entity.orderReponse.OrderEntity
import com.example.qrbnb_client.presentation.state.OrderUiState
import com.example.qrbnb_client.presentation.viewmodel.OrdersViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = koinInject(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Orders",
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
    ) { paddingValues ->
        when (uiState) {
            is OrderUiState.Loading -> {
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

            is OrderUiState.Success -> {
                val orders = (uiState as OrderUiState.Success).orders

                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF5F5F5))
                            .padding(paddingValues),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(orders) { order ->
                        OrderCard(order = order)
                    }
                }
            }

            is OrderUiState.Error -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = (uiState as OrderUiState.Error).message,
                            color = Color.Red,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: OrderEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    AsyncImage(
                        model = order.imageUrl,
                        contentDescription = order.title,
                        modifier =
                            Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFF0F0F0)),
                        contentScale = ContentScale.Crop,
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Text(
                            text = order.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                        )
                        Text(
                            text = order.subtitle,
                            fontSize = 13.sp,
                            color = Color.Gray,
                        )
                        Text(
                            text = order.description,
                            fontSize = 13.sp,
                            color = Color.Gray,
                        )
                    }
                }

                // Price
                Text(
                    text = "${"%.2f".format(order.price)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = order.status,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedButton(
                    onClick = { /* TODO: Update Status */ },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors =
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                        ),
                ) {
                    Text(
                        text = "Update Status",
                        fontSize = 14.sp,
                        color = Color.Black,
                    )
                }

                OutlinedButton(
                    onClick = { /* TODO: Cancel Order */ },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors =
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                        ),
                ) {
                    Text(
                        text = "Cancel Order",
                        fontSize = 14.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}

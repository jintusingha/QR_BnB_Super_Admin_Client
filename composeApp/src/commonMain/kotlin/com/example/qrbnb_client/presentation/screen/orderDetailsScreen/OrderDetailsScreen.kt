package com.example.qrbnb_client.presentation.screen.orderDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderDetailsEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderItemEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.TimelineStepEntity
import com.example.qrbnb_client.presentation.state.OrderDetailsUiState
import com.example.qrbnb_client.presentation.viewmodel.OrderDetailsViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(
    viewModel: OrderDetailsViewModel = koinInject(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Order Details",
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
            is OrderDetailsUiState.Loading -> {
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

            is OrderDetailsUiState.Success -> {
                val orderDetails = (uiState as OrderDetailsUiState.Success).data
                OrderDetailsContent(
                    orderDetails = orderDetails,
                    modifier = Modifier.padding(paddingValues),
                )
            }

            is OrderDetailsUiState.Error -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = (uiState as OrderDetailsUiState.Error).message,
                        color = Color.Red,
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}

@Composable
fun OrderDetailsContent(
    orderDetails: OrderDetailsEntity,
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
        Text(
            text = "Order #${orderDetails.orderId}",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )
        Text(
            text = "Placed ${orderDetails.placedAt}",
            fontSize = 13.sp,
            color = Color.Gray,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors =
                CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF3E0),
                ),
            elevation = CardDefaults.cardElevation(0.dp),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = "Customer: ${orderDetails.customer.name}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                    Text(
                        text = "Phone: ${orderDetails.customer.phone}",
                        fontSize = 13.sp,
                        color = Color.Gray,
                    )
                    Text(
                        text = "Table ${orderDetails.customer.table}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                }

                AsyncImage(
                    model = orderDetails.customer.avatar,
                    contentDescription = "Customer avatar",
                    modifier =
                        Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Ordered Items Section
        Text(
            text = "Ordered Items",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )

        orderDetails.items.forEach { item ->
            OrderItemRow(item = item)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Price Summary
        PriceSummaryRow(label = "Subtotal", amount = orderDetails.subtotal)
        PriceSummaryRow(label = "Tax", amount = orderDetails.tax)
        PriceSummaryRow(
            label = "Total",
            amount = orderDetails.total,
            isTotal = true,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Order Timeline",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )

        orderDetails.timeline.forEach { step ->
            TimelineStepItem(step = step)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Order Status",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Status: ${orderDetails.status}",
                fontSize = 14.sp,
                color = Color.Gray,
            )
            Box(
                modifier =
                    Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4CAF50)),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Button(
                onClick = { /* TODO: Mark Completed */ },
                modifier =
                    Modifier
                        .weight(1f)
                        .height(48.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7B7B),
                    ),
                shape = RoundedCornerShape(24.dp),
            ) {
                Text(
                    text = "Mark Completed",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            }

            OutlinedButton(
                onClick = { /* TODO: Cancel Order */ },
                modifier =
                    Modifier
                        .weight(1f)
                        .height(48.dp),
                shape = RoundedCornerShape(24.dp),
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

@Composable
fun OrderItemRow(item: OrderItemEntity) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
            Text(
                text = "x${item.quantity}",
                fontSize = 13.sp,
                color = Color.Gray,
            )
        }
        Text(
            text = "$%.2f".format(item.price),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
    }
}

@Composable
fun PriceSummaryRow(
    label: String,
    amount: Double,
    isTotal: Boolean = false,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            fontSize = if (isTotal) 16.sp else 14.sp,
            fontWeight = if (isTotal) FontWeight.SemiBold else FontWeight.Normal,
            color = Color.Gray,
        )
        Text(
            text = "$%.2f".format(amount),
            fontSize = if (isTotal) 16.sp else 14.sp,
            fontWeight = if (isTotal) FontWeight.SemiBold else FontWeight.Normal,
            color = Color.Black,
        )
    }
}

@Composable
fun TimelineStepItem(step: TimelineStepEntity) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = if (step.completed) Color(0xFF4CAF50) else Color.Gray,
                        shape = CircleShape,
                    ).background(
                        color = if (step.completed) Color(0xFF4CAF50) else Color.White,
                    ),
        )

        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = step.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
            Text(
                text = step.time,
                fontSize = 13.sp,
                color = Color.Gray,
            )
        }
    }
}

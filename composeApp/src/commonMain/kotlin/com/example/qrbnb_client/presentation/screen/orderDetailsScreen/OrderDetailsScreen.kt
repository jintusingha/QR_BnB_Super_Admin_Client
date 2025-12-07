package com.example.qrbnb_client.presentation.screen.orderDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderDetailsEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderItemEntity
import com.example.qrbnb_client.domain.entity.orderDetailsResponse.TimelineStepEntity
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.OrderDetailsUiState
import com.example.qrbnb_client.presentation.viewmodel.OrderDetailsViewModel
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_700
import com.example.qrbnb_client.ui.style_16_20_700
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500
import com.example.qrbnb_client.ui.style_18_23_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon
import qr_bnb_client.composeapp.generated.resources.person

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(
    orderId: String,
    viewModel: OrderDetailsViewModel = koinInject(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(orderId) {
        viewModel.loadOrder(orderId)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Order Details",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
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
                OrderDetailsContent(
                    orderDetails = (uiState as OrderDetailsUiState.Success).data,
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
            style = style_18_23_700(),
            color = Color.Black,
        )

        Text(
            text = "Placed ${orderDetails.placedAt}",
            style = style_14_21_400(),
            color = SoftBrown,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp)),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = "Customer: ${orderDetails.customer.name}",
                        style = style_16_20_700(),
                        color = Color.Black,
                    )

                    Text(
                        text = "Phone: ${orderDetails.customer.phone}",
                        style = style_14_21_400(),
                        color = SoftBrown,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TableChip(orderDetails.customer.table)
                }

                Image(
                    painter = painterResource(Res.drawable.person),
                    contentDescription = "Customer avatar",
                    modifier =
                        Modifier
                            .width(130.dp)
                            .height(93.dp)
                            .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Ordered Items", style = style_18_23_700())

        orderDetails.items.forEach { item ->
            OrderItemRow(item = item)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        PriceSummaryRow("Subtotal", orderDetails.subtotal)

        Divider(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
            color = Color(0x33000000),
            thickness = 1.dp,
        )

        PriceSummaryRow("Tax", orderDetails.tax)

        Divider(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
            color = Color(0x33000000),
            thickness = 1.dp,
        )

        PriceSummaryRow("Total", orderDetails.total, isTotal = true)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Order Timeline",
            style = style_18_23_700(),
            color = Color.Black,
        )

        orderDetails.timeline.forEachIndexed { index, step ->
            TimelineStepItem(
                step = step,
                isLast = index == orderDetails.timeline.lastIndex,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Order Status",
            style = style_18_23_700(),
            color = Black,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Status: ${orderDetails.status}",
                style = style_16_24_400(),
                color = Black,
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = {},
                modifier = Modifier.widthIn(min = 150.dp).height(48.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF5757)),
                shape = RoundedCornerShape(24.dp),
            ) {
                Text("Mark Completed", style = style_14_21_700(), color = Color.White)
            }

            CancelButton(onClick = {})
        }
    }
}

@Composable
fun OrderItemRow(item: OrderItemEntity) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(item.name, style = style_16_24_500(), color = Color.Black)
            Text("x${item.quantity}", style = style_14_21_400(), color = SoftBrown)
        }

        Text(
            text = "$%.2f".format(item.price),
            style = style_16_24_400(),
            color = Black,
        )
    }
}

@Composable
fun PriceSummaryRow(
    label: String,
    amount: Double,
    isTotal: Boolean = false,
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
    ) {
        Text(
            text = label,
            style = style_14_21_400(),
            color = SoftBrown,
        )
        Text(
            text = "$%.2f".format(amount),
            style = style_14_21_400(),
            color = Black,
        )
    }
}

@Composable
fun TimelineStepItem(
    step: TimelineStepEntity,
    isLast: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier =
                    Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Black.copy(alpha = 0.6f), CircleShape),
            )

            if (!isLast) {
                Box(
                    modifier =
                        Modifier
                            .width(2.dp)
                            .height(28.dp)
                            .background(Color(0x33000000)),
                )
            }
        }

        Column {
            Text(step.title, style = style_16_24_500(), color = Color.Black)
            Text(step.time, style = style_16_24_400(), color = SoftBrown)
        }
    }
}

@Composable
fun TableChip(tableNumber: String) {
    Box(
        modifier =
            Modifier
                .height(32.dp)
                .background(Color(0xFFF5F2F2), RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Table $tableNumber",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
    }
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    Box(
        modifier =
            Modifier
                .height(48.dp)
                .background(
                    color = Color(0xFFF5F2F2),
                    shape = RoundedCornerShape(24.dp),
                ).clickable { onClick() }
                .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Cancel Order",
            style = style_14_21_700(),
            color = Color.Black,
        )
    }
}

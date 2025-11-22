package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.OrderUiState
import com.example.qrbnb_client.presentation.viewmodel.OrdersViewModel
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_500
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@Composable
fun StatusChip(status: String) {
    Row(
        modifier =
            Modifier
                .background(
                    color = Color(0xFFF3ECEC),
                    shape = RoundedCornerShape(16.dp),
                ).padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Icon(
            imageVector = Icons.Default.AccessTime,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(18.dp),
        )

        Text(
            text = status,
            style = style_14_21_500(),
            color = Black,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = koinInject(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Orders",
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
            is OrderUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) { CircularProgressIndicator() }
            }

            is OrderUiState.Success -> {
                val orders = (uiState as OrderUiState.Success).orders

                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(paddingValues),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(orders) { order -> OrderItem(order) }
                }
            }

            is OrderUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center,
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

@Composable
fun OrderItem(order: OrderEntity) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
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

                    Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
                        Text(
                            text = order.title,
                            style = style_16_24_500(),
                            color = Color.Black,
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = order.subtitle,
                            style = style_14_21_400(),
                            color = SoftBrown,
                        )

                        Spacer(modifier = Modifier.height(1.dp))

                        Text(
                            text = order.description,
                            style = style_14_21_400(),
                            color = SoftBrown,
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "$${String.format("%.2f", order.price)}",
                        style = style_16_24_400(),
                        color = Color.Black,
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    StatusChip(order.status)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier =
                        Modifier
                            .background(
                                color = Color(0xFFF3ECEC),
                                shape = RoundedCornerShape(50),
                            ).padding(horizontal = 20.dp, vertical = 10.dp)
                            .clickable { },
                ) {
                    Text(
                        text = "Update Status",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                    )
                }

                Text(
                    text = "Cancel Order",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { },
                )
            }
        }
    }
}

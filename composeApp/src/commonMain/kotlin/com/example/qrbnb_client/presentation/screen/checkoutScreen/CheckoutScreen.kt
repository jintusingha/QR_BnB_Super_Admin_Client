

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manulOrderRequestDto.ManualOrderItemDto
import com.example.qrbnb_client.data.remote.model.manualOrderDtos.manulOrderRequestDto.ManualOrderRequestDto
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.ManualOrderUiState
import com.example.qrbnb_client.presentation.viewmodel.ManualOrderViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.icon_seating_table
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon
import qr_bnb_client.composeapp.generated.resources.menuicon

@Composable
fun CheckoutScreen(
    viewModel: ManualOrderViewModel = koinInject(),
    onCloseClick: () -> Unit,
    onOrderSuccess: (orderId: String, orderNumber: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val order = (uiState as? ManualOrderUiState.Success)?.order



    var customerName by remember { mutableStateOf("") }
    var customerPhone by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    // Fake data
    val fakeSeatingAreaId = "UUID-SEATING"
    val fakeItems = remember {
        listOf(
            ManualOrderItemDto(itemId = "UUID-ITEM-1", quantity = 2),
            ManualOrderItemDto(itemId = "UUID-ITEM-2", quantity = 1)
        )
    }


    LaunchedEffect(uiState) {
        if (uiState is ManualOrderUiState.Success) {
            val successState = uiState as ManualOrderUiState.Success
            onOrderSuccess(successState.order.orderId, successState.order.orderNumber)
        }
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
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },
        containerColor = Color.White,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 80.dp)
            ) {




                Text(
                    text = "Checkout",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                )


                InfoSection(
                    iconRes = Res.drawable.menuicon,
                    title = "Selected Items",
                    subtitle = "Item List separated by comma",
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                )


                InfoSection(
                    iconRes = Res.drawable.icon_seating_table,
                    title = "Table/Room",
                    subtitle = "Room 203",
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                )


                Text(
                    text = "Customer Name",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                )

                OutlinedTextField(
                    value = customerName,
                    onValueChange = { customerName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    placeholder = {
                        Text(text = "Optional", color = Color(0xFF9E9E9E), fontSize = 14.sp)
                    },

                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp)
                )


                Text(
                    text = "Customer Phone",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                )

                OutlinedTextField(
                    value = customerPhone,
                    onValueChange = { customerPhone = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    placeholder = {
                        Text(text = "Optional", color = Color(0xFF9E9E9E), fontSize = 14.sp)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp)
                )


                Text(
                    text = "Notes/Special Instructions",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                )

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .height(120.dp),
                    placeholder = {},
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
            ) {

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                Spacer(modifier = Modifier.height(16.dp))

                val totalAmount = when (uiState) {
                    is ManualOrderUiState.Success -> (uiState as ManualOrderUiState.Success).order.total
                    else -> 0.0
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "$${"%.2f".format(totalAmount)}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onCloseClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            width = 1.dp,
                            brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE0E0E0))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Cancel", fontSize = 16.sp)
                    }

                    Button(
                        onClick = {
                            val request = ManualOrderRequestDto(
                                seatingAreaId = fakeSeatingAreaId,
                                customerName = customerName.ifBlank { null },
                                customerPhone = customerPhone.ifBlank { null },
                                notes = notes.ifBlank { null },
                                items = fakeItems
                            )
                            viewModel.createOrder(request)
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6B6B)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        enabled = uiState !is ManualOrderUiState.Loading
                    ) {
                        if (uiState is ManualOrderUiState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(text = "Confirm Order", fontSize = 16.sp)
                        }
                    }
                }
            }

            if (uiState is ManualOrderUiState.Error) {
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    containerColor = Color(0xFFFF5252)
                ) {
                    Text(text = (uiState as ManualOrderUiState.Error).message, color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun InfoSection(
    iconRes: DrawableResource,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            when (iconRes) {
                else -> {
                    Icon(
                        painter = painterResource(iconRes),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color(0xFF757575)
            )
        }
    }
}

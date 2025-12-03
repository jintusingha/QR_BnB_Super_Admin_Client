package com.example.qrbnb_client.presentation.screen.ordersCalenderScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.domain.entity.ordersByDateEntity.OrderItemEntity
import com.example.qrbnb_client.domain.entity.ordersByDateEntity.OrdersByDateEntity
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500
import com.example.qrbnb_client.ui.style_16_24_500_
import com.example.qrbnb_client.ui.style_16_24_W400
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDate
import org.jetbrains.compose.resources.painterResource
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.closeicon
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersByDateBottomSheet(
    date: String,
    data: OrdersByDateEntity,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier.fillMaxHeight(),
        containerColor = Color.White
    ) {


        Scaffold(
            topBar = {
                CustomTopAppBar(
                    title = "Orders on ${formatPrettyDate(date)}",
                    navigationIcon = {
                        IconButton(onClick = onDismiss) {
                            Icon(
                                painter = painterResource(Res.drawable.closeicon),
                                contentDescription = "close sheet",
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(paddingValues)
                    .padding(20.dp)
            ) {


                Text(
                    text = "${data.totalOrders} orders · $${"%.2f".format(data.totalAmount)}",
                    style= style_14_21_400(),
                    color= SoftBrown
                )

                Spacer(Modifier.height(16.dp))

                Divider(color = Color(0xFFEAEAEA), thickness = 1.dp)

                Spacer(Modifier.height(16.dp))


                data.orders.forEach { order ->
                    OrderItemRow(order)
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}


@Composable
fun OrderItemRow(order: OrderItemEntity) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = order.orderNumber,
                style= style_16_24_500_(),
                        modifier = Modifier.weight(1f)
            )

            Text(
                text = "$${"%.2f".format(order.total)}",
                style= style_16_24_W400()
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = order.seatingArea,
            style=style_14_21_400(),
            color=SoftBrown
        )
    }
}
fun formatPrettyDate(dateString: String): String {
    val date = dateString.toLocalDate()
    val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }
        .take(3)

    return "$month ${date.dayOfMonth}"
}
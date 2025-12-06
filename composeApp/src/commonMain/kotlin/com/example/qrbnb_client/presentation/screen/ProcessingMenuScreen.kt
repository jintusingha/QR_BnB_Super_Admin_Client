package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_700
import com.example.qrbnb_client.ui.style_16_24_400
import org.jetbrains.compose.resources.painterResource
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.importmenu

@Composable
fun ProcessingMenuScreen(onCancelClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Import Menu",
                navigationIcon = {
                    IconButton(onClick = onCancelClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.5.dp))

            Text(
                text = "Processing Your Menu",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp),
            )

            Spacer(modifier = Modifier.weight(0.3f))

            Box(
                modifier =
                    Modifier
                        .width(358.dp)
                        .height(537.dp)
                        .background(
                            color = Color(0xFFFFE4E1),
                            shape = RoundedCornerShape(8.dp),
                        ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(Res.drawable.importmenu),
                    contentDescription = "Chef Hat",
                    modifier = Modifier.fillMaxSize(0.6f),
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "We're extracting categories, items, prices, and\nmodifiers using AI...",
                style = style_16_24_400(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "This might take a few seconds.",
                style = style_14_21_400(),
                color = SoftBrown,
            )

            Spacer(modifier = Modifier.weight(0.7f))

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onCancelClick,
                modifier =
                    Modifier
                        .width(358.dp)
                        .height(40.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF5F5F5),
                    ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                Text(
                    text = "Cancel Processing",
                    style= style_14_21_700(),
                    color = Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

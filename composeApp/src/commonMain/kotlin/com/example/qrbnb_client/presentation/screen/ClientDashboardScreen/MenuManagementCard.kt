package com.example.qrbnb_client.presentation.screen.clientdashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.qrbnb_client.ui.Black_color
import com.example.qrbnb_client.ui.jakarta_regular_14px
import com.example.qrbnb_client.ui.softRed
import com.example.qrbnb_client.ui.style_14_21_500
import com.example.qrbnb_client.ui.style_16_20_700
import com.example.qrbnb_client.ui.style_16_24_400

@Composable
fun MenuManagementCard(
    title: String,
    description: String,
    actionLabel: String,
    iconUrl: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = title,
                    style = style_16_20_700(),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = description,
                    style = jakarta_regular_14px(),
                    color = softRed,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onClick,
                    shape = RoundedCornerShape(16.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF5F0F0),
                            contentColor = Black_color,
                        ),
                    modifier =
                        Modifier
                            .height(32.dp)
                            .graphicsLayer(
                                rotationZ = 0f,
                                alpha = 1f,
                            ),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp),
                ) {
                    Text(
                        text = actionLabel,
                        style = style_14_21_500(),
                        color = Black_color,
                        maxLines = 1,
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            AsyncImage(
                model = iconUrl,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(12.dp)),
            )
        }
    }
}

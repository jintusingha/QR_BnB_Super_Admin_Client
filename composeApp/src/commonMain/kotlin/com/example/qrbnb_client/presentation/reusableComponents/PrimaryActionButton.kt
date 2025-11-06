package com.example.qrbnb_client.presentation.reusableComponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.qrbnb_client.ui.SendOtpButton

@Composable
fun PrimaryActionButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color = SendOtpButton,
    isLoading: Boolean = false,
) {
    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier =
            Modifier
                .fillMaxWidth()
                .widthIn(min = 84.dp, max = 480.dp)
                .height(40.dp),
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
    ) {
        if (isLoading) {
            androidx.compose.material3.CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 2.dp,
                modifier = Modifier.height(20.dp),
            )
        } else {
            Text(
                text = text,
//                style = Loginbtn_style(),
//                color = textColor
            )
        }
    }
}

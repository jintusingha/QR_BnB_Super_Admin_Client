package com.example.qrbnb_client.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.plusjakartasans_bold
import qr_bnb_client.composeapp.generated.resources.plusjakartasans_medium
import qr_bnb_client.composeapp.generated.resources.plusjakartasans_regular

@Composable
fun plusJakartaSans(): FontFamily {
    return FontFamily(
        Font(Res.font.plusjakartasans_bold, FontWeight.W700),
        Font(Res.font.plusjakartasans_regular, FontWeight.W400),
        Font(Res.font.plusjakartasans_medium, FontWeight.W500),
    )
}
@Composable
fun style_18_23_700(): TextStyle{
    return TextStyle(
        fontFamily = plusJakartaSans(),
        fontWeight = FontWeight.W700,
        fontSize =18.sp,
        lineHeight = 23.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center
    )
}
@Composable
fun  style_16_24_400(): TextStyle{
    return TextStyle(
        fontFamily = plusJakartaSans(),
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center

    )
}


package com.example.qrbnb_client.presentation.screen.generateQrScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.viewmodel.GenerateQrViewModel
import com.example.qrbnb_client.presentation.viewmodel.SeatingDetailViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_16_20_700
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500
import com.example.qrbnb_client.ui.style_18_23_700
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Image_size
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.layoutstyle
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon
import qr_bnb_client.composeapp.generated.resources.table

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateQrScreen(
    seatingId: String,
    onBackClick: () -> Unit = {},
    onSuccess: (qrUrl: String, deepLink: String) -> Unit = { _, _ -> },
    seatingDetailViewModel: SeatingDetailViewModel = koinInject(),
    generateQrViewModel: GenerateQrViewModel = koinInject(),
) {
    val seatingDetailUiState by seatingDetailViewModel.uiState.collectAsStateWithLifecycle()
    val generateQrUiState by generateQrViewModel.uiState.collectAsStateWithLifecycle()

    var includeRoomName by remember { mutableStateOf(true) }
    var selectedLayoutStyle by remember { mutableStateOf("Rounded") }
    var selectedSize by remember { mutableStateOf("Medium") }
    var showLayoutDropdown by remember { mutableStateOf(false) }
    var showSizeDropdown by remember { mutableStateOf(false) }

    LaunchedEffect(seatingId) {
        seatingDetailViewModel.loadSeatingDetail(seatingId)
    }

    LaunchedEffect(generateQrUiState.isSuccess) {
        if (generateQrUiState.isSuccess &&
            generateQrUiState.qrUrl != null &&
            generateQrUiState.deepLink != null
        ) {
            onSuccess(generateQrUiState.qrUrl!!, generateQrUiState.deepLink!!)
            generateQrViewModel.resetState()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Generate QR Code",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "close",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
        containerColor = Color.White,
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
        ) {
            when {
                seatingDetailUiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
                seatingDetailUiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Error: ${seatingDetailUiState.error}",
                            color = Color.Red,
                        )
                    }
                }
                seatingDetailUiState.seatingDetail != null -> {
                    val detail = seatingDetailUiState.seatingDetail!!

                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "QR lets guests order directly from this room/table",
                            style = style_16_24_400(),
                            color = Color.Black,
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        SeatingInfoCard(
                            seatingName = detail.name,
                            seatingType = detail.description,
                            imageUrl = detail.imageUrl,
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "QR Settings",
                            style = style_18_23_700(),
                            color = Color.Black,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        SettingRowWithSwitch(
                            icon = Res.drawable.table,
                            title = detail.name,
                            subtitle = "Include room/table name",
                            checked = includeRoomName,
                            onCheckedChange = { includeRoomName = it },
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        SettingRowWithDropdown(
                            icon = Res.drawable.layoutstyle,
                            title = "Layout Style",
                            subtitle = "Rounded, Minimal, Bold",
                            selectedValue = selectedLayoutStyle,
                            expanded = showLayoutDropdown,
                            onExpandedChange = { showLayoutDropdown = it },
                            options = listOf("Rounded", "Minimal", "Bold"),
                            onOptionSelected = {
                                selectedLayoutStyle = it
                                showLayoutDropdown = false
                            },
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        SettingRowWithDropdown(
                            icon = Res.drawable.Image_size,
                            title = "Size",
                            subtitle = "Small, Medium, Large",
                            selectedValue = selectedSize,
                            expanded = showSizeDropdown,
                            onExpandedChange = { showSizeDropdown = it },
                            options = listOf("Small", "Medium", "Large"),
                            onOptionSelected = {
                                selectedSize = it
                                showSizeDropdown = false
                            },
                        )

                        if (generateQrUiState.error != null) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Error: ${generateQrUiState.error}",
                                color = Color.Red,
                                fontSize = 14.sp,
                            )
                        }
                    }

                    Button(
                        onClick = {
                            generateQrViewModel.generateQr(seatingId)
                        },
                        modifier =
                            Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(16.dp)
                                .height(48.dp),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF6B6B),
                                contentColor = Color.White,
                            ),
                        shape = RoundedCornerShape(24.dp),
                        enabled = !generateQrUiState.isLoading,
                    ) {
                        if (generateQrUiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White,
                                strokeWidth = 2.dp,
                            )
                        } else {
                            Text(
                                text = "Generate QR Code",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SeatingInfoCard(
    seatingName: String,
    seatingType: String,
    imageUrl: String?,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.White),
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = seatingName,
                style = style_16_20_700(),
                color = Color.Black,
            )
            Text(
                text = seatingType,
                style = style_14_21_400(),
                color = SoftBrown,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        if (!imageUrl.isNullOrEmpty()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = seatingName,
                modifier =
                    Modifier
                        .width(130.dp)
                        .height(64.dp)
                        .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
fun SettingRowWithSwitch(
    icon: DrawableResource,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F0F0)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified,
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = style_16_24_500(),
                color = Color.Black,
            )
            Text(
                text = subtitle,
                style = style_14_21_400(),
                color = SoftBrown,
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors =
                SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFFF6B6B),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Gray,
                ),
        )
    }
}

@Composable
fun SettingRowWithDropdown(
    icon: DrawableResource,
    title: String,
    subtitle: String,
    selectedValue: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable { onExpandedChange(!expanded) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF5F0F0)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified,
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = style_16_24_500(),
                    color = Color.Black,
                )
                Text(
                    text = subtitle,
                    style = style_14_21_400(),
                    color = SoftBrown,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF5F5F5))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Text(
                    text = selectedValue,
                    fontSize = 14.sp,
                    color = Color.Black,
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "dropdown",
                    modifier = Modifier.size(20.dp),
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier.fillMaxWidth(0.4f),
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = { onOptionSelected(option) },
                )
            }
        }
    }
}

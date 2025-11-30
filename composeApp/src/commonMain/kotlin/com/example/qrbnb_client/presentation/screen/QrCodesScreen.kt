package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.qrbnb_client.data.QrDownloader
import com.example.qrbnb_client.domain.entity.qrCodesResponse.QrCodeItem
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.QrCodesUiState
import com.example.qrbnb_client.presentation.viewmodel.QrCodesViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_400_
import com.example.qrbnb_client.ui.style_16_24_500
import com.example.qrbnb_client.ui.style_18_23_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrCodesScreen(
    onBackClick: () -> Unit = {},
    onQrCodeClick: (QrCodeItem) -> Unit = {},
    viewModel: QrCodesViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadQrCodes()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "QR Codes",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "back",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "All QR Codes",
                style = style_18_23_700(),
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (uiState) {
                is QrCodesUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is QrCodesUiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Error: ${(uiState as QrCodesUiState.Error).message}",
                            color = Color.Red,
                        )
                    }
                }

                is QrCodesUiState.Success -> {
                    val qrCodes = (uiState as QrCodesUiState.Success).data

                    if (qrCodes.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "No QR codes generated yet",
                                style = style_14_21_400(),
                                color = SoftBrown,
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(bottom = 16.dp),
                        ) {
                            items(qrCodes) { qrCode ->
                                QrCodeCard(
                                    qrCode = qrCode,
                                    onClick = { onQrCodeClick(qrCode) },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QrCodeCard(
    qrCode: QrCodeItem,
    downloader: QrDownloader = koinInject(),
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onClick() },
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF4A6B6B)),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = qrCode.qrCodeUrl,
                contentDescription = qrCode.name,
                modifier =
                    Modifier
                        .fillMaxSize(0.5f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                contentScale = ContentScale.Fit,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = qrCode.name,
            style = style_16_24_500(),
            color = Color.Black,
        )

        Text(
            text = "Created on ${formatDate(qrCode.createdAt)}",
            style = style_14_21_400_(),
            color = SoftBrown,
        )

        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Download",
                fontSize = 12.sp,
                color = SoftBrown,
                modifier = Modifier.clickable { downloader.downloadImage(qrCode.imageUrl) },
            )

            Text("•", fontSize = 12.sp, color = SoftBrown)

            Text(
                text = "View",
                fontSize = 12.sp,
                color = SoftBrown,
                modifier = Modifier.clickable { /* View */ },
            )

            Text("•", fontSize = 12.sp, color = SoftBrown)

            Text(
                text = "Delete",
                fontSize = 12.sp,
                color = SoftBrown,
                modifier = Modifier.clickable { /* Delete */ },
            )
        }
    }
}

fun formatDate(dateString: String): String = dateString.substringBefore("T")

package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
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
import com.example.qrbnb_client.domain.entity.menuConfigurationResponse.MenuConfigItem
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.MenuUiState
import com.example.qrbnb_client.presentation.viewmodel.MenuConfigurationViewModel
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_500
import com.example.qrbnb_client.ui.style_16_20_700
import com.example.qrbnb_client.ui.style_16_24_400_
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.filter
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuConfigurationScreen(
    viewModel: MenuConfigurationViewModel = koinInject(),
    onNavigateBack: () -> Unit = {},
    onManageClick: (String) -> Unit = {},
    onAddClick: (String) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomTopAppBar(
                title = "Menu Configuration",
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.filter),
                            contentDescription = "Filter",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
    ) { paddingValues ->

        when (uiState) {
            is MenuUiState.Loading -> {
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

            is MenuUiState.Error -> {
                val msg = (uiState as MenuUiState.Error).message
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = msg, color = Color.Red)
                }
            }

            is MenuUiState.Success -> {
                val items = (uiState as MenuUiState.Success).items

                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item {
                        Text(
                            text = "Configure your menu items with badges, tags, modifier groups, and variants.",
                            style = style_16_24_400_(),
                            color = Black,
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }


                    items(items) { item ->
                        MenuConfigurationCard(
                            item = item,
                            onManageClick = { onManageClick(item.id) },
                            onAddClick = { onAddClick(item.id) },
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 0.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MenuConfigurationCard(
    item: MenuConfigItem,
    onManageClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                modifier = Modifier.weight(1f).height(112.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = item.sectionTitle,
                        style= style_14_21_400(),
                        color= SoftBrown
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = item.title,
                       style= style_16_20_700(),
                        color=Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item.description,
                        style=style_14_21_400(),
                        color=SoftBrown
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFF5F5F5))
                        .clickable { onManageClick() }
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                ) {
                    Text(
                        text = "Manage",
                        style= style_14_21_500(),
                        color=Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item.imageUrl?.let { url ->
                    Box(
                        modifier =
                            Modifier
                                .size(112.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(getBackgroundColor(item.title)),
                    ) {
                        AsyncImage(
                            model = url,
                            contentDescription = item.title,
                            contentScale = ContentScale.Fit,
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onAddClick,
                    modifier =
                        Modifier
                            .height(40.dp)
                            .widthIn(min = 84.dp, max = 480.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF75C5C),
                        ),
                    contentPadding =
                        PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                        ),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Add",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
private fun getBackgroundColor(title: String): Color =
    when {
        title.contains("Badges", ignoreCase = true) -> Color(0xFFFFE5CC)
        title.contains("Tags", ignoreCase = true) -> Color(0xFF4A7C7E)
        title.contains("Modifier", ignoreCase = true) -> Color(0xFFE8F5E9)
        title.contains("Variants", ignoreCase = true) -> Color(0xFFF5F5F5)
        else -> Color(0xFFE3F2FD)
    }
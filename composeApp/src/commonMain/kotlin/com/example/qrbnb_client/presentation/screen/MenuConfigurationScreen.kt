package com.example.qrbnb_client.presentation.screen

import androidx.compose.foundation.background
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
import com.example.qrbnb_client.presentation.state.MenuUiState
import com.example.qrbnb_client.presentation.viewmodel.MenuConfigurationViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuConfigurationScreen(
    viewModel: MenuConfigurationViewModel = koinInject(),
    onNavigateBack: () -> Unit = {},
    onManageClick: (String) -> Unit = {},
    onAddClick: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Menu Configuration",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is MenuUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Loading Configuration...")
                    }
                }
            }

            is MenuUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(
                            text = "Error",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF44336)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = (uiState as MenuUiState.Error).message,
                            color = Color(0xFFF44336)
                        )
                    }
                }
            }

            is MenuUiState.Success -> {
                val items = (uiState as MenuUiState.Success).items

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Header Text
                    item {
                        Text(
                            text = "Configure your menu items with badges, tags, modifier groups, and variants.",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            lineHeight = 18.sp
                        )
                    }

                    // Display all items
                    items(items) { item ->
                        MenuConfigurationCard(
                            item = item,
                            onManageClick = { onManageClick(item.id) },
                            onAddClick = { onAddClick(item.id) }
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
    onAddClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side - Text content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Section Title (small gray text)
                Text(
                    text = item.sectionTitle,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.description,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Manage button
                TextButton(
                    onClick = onManageClick,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Manage",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Right side - Image and Add button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Image
                item.imageUrl?.let { url ->
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(getBackgroundColor(item.title))
                    ) {
                        AsyncImage(
                            model = url,
                            contentDescription = item.title,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )
                    }
                }

                // Add button
                Button(
                    onClick = onAddClick,
                    modifier = Modifier
                        .height(32.dp)
                        .widthIn(min = 80.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF75C5C)
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Add",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun getBackgroundColor(title: String): Color {
    return when {
        title.contains("Badges", ignoreCase = true) -> Color(0xFFFFE5CC)
        title.contains("Tags", ignoreCase = true) -> Color(0xFF4A7C7E)
        title.contains("Modifier", ignoreCase = true) -> Color(0xFFE8F5E9)
        title.contains("Variants", ignoreCase = true) -> Color(0xFFF5F5F5)
        else -> Color(0xFFE3F2FD)
    }
}
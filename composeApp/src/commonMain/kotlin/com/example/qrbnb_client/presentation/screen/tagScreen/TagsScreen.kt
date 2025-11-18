package com.example.qrbnb_client.presentation.screen.tagScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.domain.entity.tagResponse.TagEntity
import com.example.qrbnb_client.presentation.state.TagUiState
import com.example.qrbnb_client.presentation.viewmodel.TagViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsScreen(
    viewModel: TagViewModel= koinInject(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    var newTagText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tags",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                    ),
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
        ) {
            // Subtitle
            Text(
                text = "Manage item categorization",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
            )

            // Existing Tags Section
            Text(
                text = "Existing Tags",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            when (uiState) {
                is TagUiState.Loading -> {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is TagUiState.Success -> {
                    val tags = (uiState as TagUiState.Success).tags

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(bottom = 24.dp),
                    ) {
                        items(tags) { tag ->
                            TagChip(tagName = tag.name)
                        }
                    }
                }

                is TagUiState.Error -> {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = (uiState as TagUiState.Error).message,
                            color = Color.Red,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Add New Tag Section
            Text(
                text = "Add New Tag",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            // Input Field
            OutlinedTextField(
                value = newTagText,
                onValueChange = { newTagText = it },
                placeholder = {
                    Text(
                        text = "Enter new tag",
                        color = Color.Gray,
                    )
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                colors =
                    OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                    ),
                shape = RoundedCornerShape(8.dp),
            )

            // Add Tag Button
            Button(
                onClick = { /* TODO: Add functionality later */ },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7B7B),
                    ),
                shape = RoundedCornerShape(28.dp),
            ) {
                Text(
                    text = "Add Tag",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun TagChip(tagName: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFF5F5F5),
        modifier =
            Modifier
                .height(40.dp)
                .wrapContentWidth(),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = tagName,
                fontSize = 14.sp,
                color = Color.Black,
            )
        }
    }
}

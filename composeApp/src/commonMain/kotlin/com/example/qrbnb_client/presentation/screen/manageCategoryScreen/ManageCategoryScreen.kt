package com.example.qrbnb_client.presentation.screen.manageCategoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.qrbnb_client.domain.entity.manageCategoryResponse.Category
import com.example.qrbnb_client.navigation.ScreenRoute
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.ManageCategoryUiState
import com.example.qrbnb_client.presentation.utility.formatDate
import com.example.qrbnb_client.presentation.viewmodel.ManageCategoryViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_16_24_500
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.add
import qr_bnb_client.composeapp.generated.resources.hamburger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCategoriesScreen(
    onAddCategoryClick: () -> Unit,
    onCategoryClick: (String) -> Unit,
    viewModel: ManageCategoryViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomTopAppBar(
                title = "Manage Categories",
                navigationIcon = {
                    IconButton({}) {
                        Icon(
                            painter = painterResource(Res.drawable.hamburger),
                            contentDescription = "hamburgericon",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.add),
                            contentDescription = "infoicon",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddCategoryClick,
                containerColor = Color(0xFFF75C5C),
                contentColor = Color.White,
                shape = RoundedCornerShape(28.dp),
                modifier =
                    Modifier
                        .width(64.dp)
                        .height(56.dp),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, end = 24.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Order",
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        },
    ) { paddingValues ->
        when (uiState) {
            is ManageCategoryUiState.Loading -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Loading Categories...")
                    }
                }
            }

            is ManageCategoryUiState.Error -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp),
                    ) {
                        Text(
                            text = "Error",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF44336),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = (uiState as ManageCategoryUiState.Error).message,
                            color = Color(0xFFF44336),
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(onClick = { viewModel.getCategories() }) {
                            Text("Retry")
                        }
                    }
                }
            }

            is ManageCategoryUiState.Success -> {
                val categories = (uiState as ManageCategoryUiState.Success).categories

                if (categories.isEmpty()) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(24.dp),
                        ) {
                            Text(
                                text = "No Categories Yet",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Tap + to add your first category",
                                fontSize = 14.sp,
                                color = Color.Gray,
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(13.5.dp),
                    ) {
                        items(categories) { category ->
                            CategoryListItem(
                                category = category,
                                onCategoryClick = { id ->
                                    onCategoryClick(id)
                                },
                                onDeleteClick = {
                                    viewModel.deleteCategory(category.id)

                                    println("Delete category: ${category.id}")
                                },
                            )
//                            HorizontalDivider(
//                                modifier = Modifier.padding(horizontal = 16.dp),
//                                color = Color(0xFFE0E0E0)
//                            )
                        }
                    }
                }
            }
        }
    }
}

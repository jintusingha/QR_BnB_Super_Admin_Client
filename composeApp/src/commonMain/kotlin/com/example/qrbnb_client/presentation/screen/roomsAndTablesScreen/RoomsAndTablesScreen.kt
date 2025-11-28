package com.example.qrbnb_client.presentation.screen.roomsAndTablesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.qrbnb_client.domain.entity.seatingAreasEntity.RoomEntity
import com.example.qrbnb_client.domain.entity.seatingAreasEntity.TableEntity
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.viewmodel.SeatingAreasViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.TabBackground
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_16_20_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomsAndTablesScreen(
    onBackClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    viewModel: SeatingAreasViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedTab by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.loadSeatingAreas()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Rooms & Tables",
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
        floatingActionButton = {
            AddRoomTableFloatingButton(onClick = onAddClick)
        },
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues),
        ) {
            Text(
                text = "Manage seating & QR access points",
                style = style_14_21_400(),
                color = SoftBrown,
                modifier =
                    Modifier
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomTabRow(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
            )

            Spacer(modifier = Modifier.height(12.dp))

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Error: ${uiState.error}",
                            color = Color.Red,
                        )
                    }
                }
                else -> {
                    if (selectedTab == 0) {
                        RoomsList(rooms = uiState.rooms)
                    } else {
                        TablesList(tables = uiState.tables)
                    }
                }
            }
        }
    }
}

@Composable
fun AddRoomTableFloatingButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFFFF6B6B),
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
                contentDescription = "Add Room/Table",
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

@Composable
fun CustomTabRow(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(TabBackground, RoundedCornerShape(25.dp))
                .padding(4.dp),
    ) {
        TabButton(
            text = "Rooms",
            isSelected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            modifier = Modifier.weight(1f),
        )
        TabButton(
            text = "Tables",
            isSelected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = if (isSelected) Color.White else Color.Transparent,
                contentColor = if (isSelected) Color.Black else Color.Gray,
            ),
        elevation = if (isSelected) ButtonDefaults.buttonElevation(4.dp) else ButtonDefaults.buttonElevation(0.dp),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier,
    ) {
        Text(text)
    }
}

@Composable
fun RoomsList(rooms: List<RoomEntity>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(rooms) { room ->
            RoomCard(room = room)
        }
    }
}

@Composable
fun RoomCard(room: RoomEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "QR Status: ${if (room.isActive) "Active" else "Inactive"}",
                    style = style_14_21_400(),
                    color = SoftBrown,
                )

                Text(
                    text = room.name,
                    style = style_16_20_700(),
                    modifier = Modifier.padding(vertical = 4.dp),
                )

                Text(
                    text = "${room.capacity} Tables",
                    style = style_14_21_400(),
                    color = SoftBrown,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier =
                        Modifier
                            .height(32.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFF5F0F0))
                            .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ActionItem("Edit") {}
                    DividerText()
                    ActionItem("Delete") {}
                    DividerText()
                    ActionItem("Generate QR") {}
                    DividerText()
                    ActionItem("View QR") {}
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            AsyncImage(
                model = room.imageUrl,
                contentDescription = room.name,
                modifier =
                    Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
fun TablesList(tables: List<TableEntity>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(tables) { table ->
            TableCard(table = table)
        }
    }
}

@Composable
fun TableCard(table: TableEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "QR Status: ${if (table.isActive) "Active" else "Inactive"}",
                    style = style_14_21_400(),
                    color = SoftBrown,
                )

                Text(
                    text = table.name,
                    style = style_16_20_700(),
                    modifier = Modifier.padding(vertical = 4.dp),
                )

                Text(
                    text = "${table.capacity} seats",
                    style = style_14_21_400(),
                    color = SoftBrown,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier =
                        Modifier
                            .height(32.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFF5F0F0))
                            .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ActionItem("Edit") {}
                    DividerText()
                    ActionItem("Delete") {}
                    DividerText()
                    ActionItem("Generate QR") {}
                    DividerText()
                    ActionItem("View QR") {}
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            AsyncImage(
                model = table.imageUrl,
                contentDescription = table.name,
                modifier =
                    Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
fun ActionItem(
    text: String,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color.Black,
        modifier =
            Modifier
                .padding(horizontal = 4.dp)
                .clickable(onClick = onClick),
    )
}

@Composable
fun DividerText() {
    Text(
        text = "|",
        fontSize = 12.sp,
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 2.dp),
    )
}

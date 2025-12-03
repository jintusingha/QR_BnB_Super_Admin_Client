package com.example.qrbnb_client.presentation.screen.ordersCalendarScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.screen.ordersCalenderScreen.OrdersByDateBottomSheet
import com.example.qrbnb_client.presentation.screen.ordersCalenderScreen.YearMonth
import com.example.qrbnb_client.presentation.viewmodel.OrdersCalendarViewModel
import com.example.qrbnb_client.ui.Black
import com.example.qrbnb_client.ui.style_13_20_700_
import com.example.qrbnb_client.ui.style_18_23_700_
import kotlinx.datetime.*
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersCalendarScreen(
    onBackClick: () -> Unit,
    viewModel: OrdersCalendarViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val today =
        Clock.System
            .now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date

    var selectedDate by remember { mutableStateOf(today) }
    var currentMonth by remember { mutableStateOf(YearMonth(today.year, today.month)) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Orders Calendar",
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
                    .padding(20.dp),
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = "Track orders by date & view daily breakdowns",
                style = style_18_23_700_(),
                color = Black,
                modifier = Modifier.padding(bottom = 16.dp),
            )

            Spacer(Modifier.height(24.dp))

            MonthHeader(
                currentMonth = currentMonth,
                onPrevMonth = { currentMonth = currentMonth.previousMonth() },
                onNextMonth = { currentMonth = currentMonth.nextMonth() },
            )

            Spacer(Modifier.height(16.dp))

            DaysOfWeekHeader()

            Spacer(Modifier.height(12.dp))

            CalendarGrid(
                currentMonth = currentMonth,
                selectedDate = selectedDate,
                today = today,
                onDateClick = { clickedDate ->
                    selectedDate = clickedDate
                    viewModel.fetchOrdersByDate(clickedDate.toString())
                    showBottomSheet = true
                },
            )
        }
    }

    if (showBottomSheet) {
        when {
            uiState.isLoading -> {
                ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(40.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            uiState.errorMessage != null -> {
                ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(40.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("Error: ${uiState.errorMessage}")
                    }
                }
            }

            uiState.data != null -> {
                OrdersByDateBottomSheet(
                    date = selectedDate.toString(),
                    data = uiState.data!!,
                    onDismiss = { showBottomSheet = false },
                )
            }
        }
    }
}

@Composable
fun MonthHeader(
    currentMonth: YearMonth,
    onPrevMonth: () -> Unit,
    onNextMonth: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onPrevMonth) {
            Icon(
                painter = painterResource(Res.drawable.leftArrowIcon),
                contentDescription = "Previous",
                modifier = Modifier.size(20.dp),
            )
        }

        Text(
            text = "${currentMonth.month.name.lowercase().replaceFirstChar { it.uppercase() }} ${currentMonth.year}",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )

        IconButton(onClick = onNextMonth) {
            Icon(
                painter = painterResource(Res.drawable.leftArrowIcon),
                contentDescription = "Next",
                modifier =
                    Modifier
                        .size(20.dp)
                        .graphicsLayer(rotationZ = 180f),
            )
        }
    }
}

@Composable
fun DaysOfWeekHeader() {
    val days = listOf("S", "M", "T", "W", "T", "F", "S")

    Row(modifier = Modifier.fillMaxWidth()) {
        days.forEach { day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                style = style_13_20_700_(),
                color = Black,
            )
        }
    }
}

@Composable
fun CalendarGrid(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    today: LocalDate,
    onDateClick: (LocalDate) -> Unit,
) {
    val firstDayOfMonth = LocalDate(currentMonth.year, currentMonth.month, 1)
    val daysInMonth =
        when (currentMonth.month) {
            Month.JANUARY, Month.MARCH, Month.MAY, Month.JULY,
            Month.AUGUST, Month.OCTOBER, Month.DECEMBER,
            -> 31
            Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> 30
            Month.FEBRUARY ->
                if (currentMonth.year % 4 == 0 &&
                    (currentMonth.year % 100 != 0 || currentMonth.year % 400 == 0)
                ) {
                    29
                } else {
                    28
                }
        }

    val firstDayOfWeek = (firstDayOfMonth.dayOfWeek.ordinal + 1) % 7
    val totalCells = firstDayOfWeek + daysInMonth
    val rows = (totalCells + 6) / 7

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        var dayCounter = 1

        repeat(rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                for (i in 0 until 7) {
                    val cellIndex = (it * 7) + i

                    if (cellIndex < firstDayOfWeek || dayCounter > daysInMonth) {
                        Box(modifier = Modifier.weight(1f).height(48.dp))
                    } else {
                        val date = LocalDate(currentMonth.year, currentMonth.month, dayCounter)

                        DayCell(
                            date = date,
                            isSelected = date == selectedDate,
                            isToday = date == today,
                            onClick = { onDateClick(date) },
                            modifier = Modifier.weight(1f),
                        )

                        dayCounter++
                    }
                }
            }
        }
    }
}

@Composable
fun DayCell(
    date: LocalDate,
    isSelected: Boolean,
    isToday: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .height(48.dp)
                .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
                Modifier
                    .size(36.dp)
                    .background(
                        color =
                            when {
                                isSelected || isToday -> Color(0xFFFF6B6B)
                                else -> Color.Transparent
                            },
                        shape = CircleShape,
                    ).clickable { onClick() },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                fontSize = 16.sp,
                fontWeight = if (isSelected || isToday) FontWeight.Medium else FontWeight.Normal,
                color = if (isSelected || isToday) Color.White else Color.Black,
            )
        }
    }
}

package com.example.qrbnb_client.domain.entity.orderListResponse

data class OrdersListResponse(
    val success: Boolean,
    val message: String,
    val data: OrderListData,
)

data class OrderListData(
    val summary: OrderListSummary,
    val orders: List<OrderListItem>,
)

data class OrderListSummary(
    val new: Int,
    val preparing: Int,
    val ready: Int,
    val delivered: Int,
)

data class OrderListItem(
    val orderId: String,
    val orderNumber: String,
    val status: String,
    val timeAgo: String,
    val seatingArea: SeatingArea?,
    val guest: OrderGuest,
    val items: OrderItems,
    val thumbnail: String,
    val actions: List<String>,
    val clientId: String,
)

data class OrderGuest(
    val name: String,
)

data class OrderItems(
    val summary: String,
    val count: Int,
)
data class SeatingArea(
    val type: String,
    val name: String
)
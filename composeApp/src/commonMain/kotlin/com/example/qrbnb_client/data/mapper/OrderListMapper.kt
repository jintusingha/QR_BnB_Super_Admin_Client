package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.orderListDto.OrderGuestDto
import com.example.qrbnb_client.data.remote.model.orderListDto.OrderItemsDto
import com.example.qrbnb_client.data.remote.model.orderListDto.OrderListDataDto
import com.example.qrbnb_client.data.remote.model.orderListDto.OrderListItemDto
import com.example.qrbnb_client.data.remote.model.orderListDto.OrderListResponseDto
import com.example.qrbnb_client.data.remote.model.orderListDto.OrderListSummaryDto
import com.example.qrbnb_client.data.remote.model.orderListDto.SeatingAreaDto
import com.example.qrbnb_client.domain.entity.orderListResponse.OrderGuest
import com.example.qrbnb_client.domain.entity.orderListResponse.OrderItems
import com.example.qrbnb_client.domain.entity.orderListResponse.OrderListData
import com.example.qrbnb_client.domain.entity.orderListResponse.OrderListItem
import com.example.qrbnb_client.domain.entity.orderListResponse.OrderListSummary
import com.example.qrbnb_client.domain.entity.orderListResponse.OrdersListResponse
import com.example.qrbnb_client.domain.entity.orderListResponse.SeatingArea

fun OrderListResponseDto.toDomain()= OrdersListResponse(
    success = success,
    message = message,
    data = data.toDomain()
)
fun OrderListDataDto.toDomain() = OrderListData(
    summary = summary.toDomain(),
    orders = orders.map { it.toDomain() }
)

fun OrderListSummaryDto.toDomain() = OrderListSummary(
    new = new,
    preparing = preparing,
    ready = ready,
    delivered = delivered
)

fun OrderListItemDto.toDomain() = OrderListItem(
    orderId = orderId,
    orderNumber = orderNumber,
    status = status,
    timeAgo = timeAgo,
    seatingArea = seatingArea?.toDomain(),
    guest = guest.toDomain(),
    items = items.toDomain(),
    thumbnail = thumbnail,
    actions = actions,
    clientId = clientId
)

fun OrderGuestDto.toDomain() = OrderGuest(
    name = name
)

fun OrderItemsDto.toDomain() = OrderItems(
    summary = summary,
    count = count
)
fun SeatingAreaDto.toDomain() = SeatingArea(
    type = type,
    name = name
)

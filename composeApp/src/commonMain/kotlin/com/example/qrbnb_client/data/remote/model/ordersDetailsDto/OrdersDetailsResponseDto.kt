package com.example.qrbnb_client.data.remote.model.ordersDetailsDto


import kotlinx.serialization.Serializable

@Serializable
data class OrdersDetailsResponseDto(
    val success: Boolean,
    val message: String?,
    val data: OrderDetailsDto
)

@Serializable
data class OrderDetailsDto(
    val orderId: String,
    val createdAt: String,
    val customer: CustomerDto,
    val items: List<OrderItemDto>,
    val summary: SummaryDto,
    val timeline: List<TimelineDto>,
    val metadata: Map<String, String>?

)
@Serializable
data class CustomerDto(
    val name: String,
    val phone: String,
    val seatingArea: SeatingAreaDto
)
@Serializable
data class SeatingAreaDto(
    val type: String,
    val name: String
)
@Serializable
data class OrderItemDto(
    val name: String,
    val quantity: Int,
    val priceEach: Double,
    val subtotal: Double,
    val notes: String
)
@Serializable
data class SummaryDto(
    val subtotal: Double,
    val taxes: Double,
    val total: Double
)
@Serializable
data class TimelineDto(
    val status: String,
    val time: String
)

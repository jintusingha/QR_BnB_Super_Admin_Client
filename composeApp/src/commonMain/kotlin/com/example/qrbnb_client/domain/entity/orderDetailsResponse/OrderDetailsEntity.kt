package com.example.qrbnb_client.domain.entity.orderDetailsResponse

data class OrderDetailsEntity(
    val orderId: String,
    val placedAt: String,
    val customer: CustomerEntity,
    val items: List<OrderItemEntity>,
    val subtotal: Double,
    val tax: Double,
    val total: Double,
    val timeline: List<TimelineStepEntity>,
    val status: String,
)

data class CustomerEntity(
    val name: String,
    val phone: String,
    val table: String,
    val avatar: String,
)

data class OrderItemEntity(
    val name: String,
    val quantity: Int,
    val price: Double,
)

data class TimelineStepEntity(
    val title: String,
    val time: String,
    val completed: Boolean,
)

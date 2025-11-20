package com.example.qrbnb_client.data.remote.model.ordersDetailsDto

import com.example.qrbnb_client.data.remote.model.orderResponseDto.OrderItemDto

data class OrdersDetailsResponseDto(
    val success: Boolean,
    val data:OrderDetailsDto
)

data class OrderDetailsDto(
    val orderId:String,
    val placedAt:String,
    val customer:CustomerDto,
    val items:List<OrderItemsDto>,
    val subtotal:Double,
    val tax:Double,
    val total:Double,
    val timeline:List<TimeLineStepDto>,
    val status:String
)

data class CustomerDto(
    val name:String,
    val phone:String,
    val table:String,
    val avatar:String
)
data class OrderItemsDto(
    val name:String,
    val quantity:Int,
    val price:Double
)
data class TimeLineStepDto(
    val title:String,
    val time:String,
    val completed:Boolean
)
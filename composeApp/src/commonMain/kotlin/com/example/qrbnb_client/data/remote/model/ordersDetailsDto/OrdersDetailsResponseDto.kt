package com.example.qrbnb_client.data.remote.model.ordersDetailsDto

import com.example.qrbnb_client.data.remote.model.orderResponseDto.OrderItemDto
import kotlinx.serialization.Serializable

@Serializable
data class OrdersDetailsResponseDto(
    val success: Boolean,
    val data:OrderDetailsDto
)
@Serializable
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
@Serializable
data class CustomerDto(
    val name:String,
    val phone:String,
    val table:String,
    val avatar:String
)
@Serializable
data class OrderItemsDto(
    val name:String,
    val quantity:Int,
    val price:Double
)
@Serializable
data class TimeLineStepDto(
    val title:String,
    val time:String,
    val completed:Boolean
)
package com.example.qrbnb_client.data.remote.service.orderDetailsDatasource

import com.example.qrbnb_client.data.remote.model.orderResponseDto.OrderItemDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.CustomerDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrderDetailsDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrderItemsDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.OrdersDetailsResponseDto
import com.example.qrbnb_client.data.remote.model.ordersDetailsDto.TimeLineStepDto

class OrderDetailsRemoteDatasourceImpl : OrderDetailsRemoteDatasource {
    override suspend fun getOrderDetails(orderId: String): OrdersDetailsResponseDto =
        OrdersDetailsResponseDto(
            success = true,
            data =
                OrderDetailsDto(
                    orderId = "12345",
                    placedAt = "10:30 AM",
                    customer =
                        CustomerDto(
                            name = "Ethan Carter",
                            phone = "(555) 123–4567",
                            table = "5",
                            avatar = "https://example.com/avatar.png",
                        ),
                    items =
                        listOf(
                            OrderItemsDto(
                                name = "Classic Burger",
                                quantity = 2,
                                price = 12.00,
                            ),
                            OrderItemsDto(
                                name = "Fries",
                                quantity = 1,
                                price = 4.00,
                            ),
                            OrderItemsDto(
                                name = "Coke",
                                quantity = 1,
                                price = 2.50,
                            ),
                        ),
                    subtotal = 18.50,
                    tax = 1.50,
                    total = 20.00,
                    timeline =
                        listOf(
                            TimeLineStepDto(
                                title = "Order Received",
                                time = "10:30 AM",
                                completed = true,
                            ),
                            TimeLineStepDto(
                                title = "Preparing",
                                time = "10:35 AM",
                                completed = true,
                            ),
                            TimeLineStepDto(
                                title = "Out for Delivery",
                                time = "10:45 AM",
                                completed = false,
                            ),
                        ),
                    status = "In Progress",
                ),
        )
}

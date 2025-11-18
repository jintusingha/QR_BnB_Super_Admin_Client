package com.example.qrbnb_client.data.remote.service.orderRemoteDatasource

import com.example.qrbnb_client.data.remote.model.orderResponseDto.OrderItemDto
import com.example.qrbnb_client.data.remote.model.orderResponseDto.OrderResponseDto

class OrderRemoteDatasourceImpl : OrderRemoteDatasource {
    override suspend fun getOrder(): OrderResponseDto {
        val dummyOrders =
            listOf(
                OrderItemDto(
                    id = "123",
                    title = "Latte",
                    subtitle = "Order #1001",
                    description = "Freshly brewed latte",
                    price = 4.50,
                    status = "pending",
                    imageUrl = "https://picsum.photos/200?1",
                ),
                OrderItemDto(
                    id = "124",
                    title = "Cappuccino",
                    subtitle = "Order #1002",
                    description = "Hot cappuccino with foam",
                    price = 5.00,
                    status = "completed",
                    imageUrl = "https://picsum.photos/200?2",
                ),
                OrderItemDto(
                    id = "125",
                    title = "Mocha",
                    subtitle = "Order #1003",
                    description = "Chocolate-flavored mocha",
                    price = 5.50,
                    status = "cancelled",
                    imageUrl = "https://picsum.photos/200?3",
                ),
                OrderItemDto(
                    id = "126",
                    title = "Americano",
                    subtitle = "Order #1004",
                    description = "Strong americano",
                    price = 3.20,
                    status = "pending",
                    imageUrl = "https://picsum.photos/200?4",
                ),
                OrderItemDto(
                    id = "127",
                    title = "Espresso",
                    subtitle = "Order #1005",
                    description = "Single shot espresso",
                    price = 2.00,
                    status = "completed",
                    imageUrl = "https://picsum.photos/200?5",
                ),
                OrderItemDto(
                    id = "128",
                    title = "Cold Coffee",
                    subtitle = "Order #1006",
                    description = "Cold brew with ice",
                    price = 4.00,
                    status = "pending",
                    imageUrl = "https://picsum.photos/200?6",
                ),
                OrderItemDto(
                    id = "129",
                    title = "Black Coffee",
                    subtitle = "Order #1007",
                    description = "Hot black coffee",
                    price = 2.50,
                    status = "completed",
                    imageUrl = "https://picsum.photos/200?7",
                ),
                OrderItemDto(
                    id = "130",
                    title = "Caramel Latte",
                    subtitle = "Order #1008",
                    description = "Latte with caramel syrup",
                    price = 5.50,
                    status = "pending",
                    imageUrl = "https://picsum.photos/200?8",
                ),
                OrderItemDto(
                    id = "131",
                    title = "Vanilla Frappe",
                    subtitle = "Order #1009",
                    description = "Cold vanilla frappe",
                    price = 6.00,
                    status = "completed",
                    imageUrl = "https://picsum.photos/200?9",
                ),
                OrderItemDto(
                    id = "132",
                    title = "Hazelnut Coffee",
                    subtitle = "Order #1010",
                    description = "Hazelnut flavored coffee",
                    price = 5.20,
                    status = "pending",
                    imageUrl = "https://picsum.photos/200?10",
                ),
            )

        return OrderResponseDto(
            success = true,
            data = dummyOrders,
        )
    }
}

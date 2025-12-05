package com.example.qrbnb_client.data.remote.service.stockItemDataSource

import com.example.qrbnb_client.data.remote.model.stockItemDto.StockItemDto
class StockItemDataSourceImpl : StockItemDataSource {

    override suspend fun getStockItems(): List<StockItemDto> {
        return listOf(
            StockItemDto(
                id = 1,
                name = "Tomatoes",
                price = 2.5,
                quantity = 100,
                unit = "units",
                category = "FOOD",
                imageUrl = "https://example.com/tomato.jpg"
            ),
            StockItemDto(
                id = 2,
                name = "Onions",
                price = 1.2,
                quantity = 80,
                unit = "units",
                category = "FOOD",
                imageUrl = "https://example.com/onion.jpg"
            ),
            StockItemDto(
                id = 3,
                name = "Chicken Breast",
                price = 5.5,
                quantity = 40,
                unit = "kg",
                category = "FOOD",
                imageUrl = "https://example.com/chicken.jpg"
            ),
            StockItemDto(
                id = 4,
                name = "Eggs",
                price = 3.0,
                quantity = 60,
                unit = "units",
                category = "FOOD",
                imageUrl = "https://example.com/eggs.jpg"
            ),
            StockItemDto(
                id = 5,
                name = "Milk",
                price = 1.8,
                quantity = 35,
                unit = "liters",
                category = "BEVERAGES",
                imageUrl = "https://example.com/milk.jpg"
            ),
            StockItemDto(
                id = 6,
                name = "Orange Juice",
                price = 2.4,
                quantity = 25,
                unit = "liters",
                category = "BEVERAGES",
                imageUrl = "https://example.com/oj.jpg"
            ),
            StockItemDto(
                id = 7,
                name = "Coffee Beans",
                price = 15.0,
                quantity = 15,
                unit = "kg",
                category = "BEVERAGES",
                imageUrl = "https://example.com/coffee.jpg"
            ),
            StockItemDto(
                id = 8,
                name = "Green Tea",
                price = 4.2,
                quantity = 20,
                unit = "packets",
                category = "BEVERAGES",
                imageUrl = "https://example.com/greentea.jpg"
            ),
            StockItemDto(
                id = 9,
                name = "Sugar",
                price = 2.0,
                quantity = 50,
                unit = "kg",
                category = "SUPPLIES",
                imageUrl = "https://example.com/sugar.jpg"
            ),
            StockItemDto(
                id = 10,
                name = "Salt",
                price = 0.7,
                quantity = 70,
                unit = "kg",
                category = "SUPPLIES",
                imageUrl = "https://example.com/salt.jpg"
            ),
            StockItemDto(
                id = 11,
                name = "Paper Cups",
                price = 3.5,
                quantity = 200,
                unit = "units",
                category = "SUPPLIES",
                imageUrl = "https://example.com/cups.jpg"
            ),
            StockItemDto(
                id = 12,
                name = "Napkins",
                price = 1.0,
                quantity = 150,
                unit = "units",
                category = "SUPPLIES",
                imageUrl = "https://example.com/napkins.jpg"
            )
        )
    }
}

package com.example.qrbnb_client.data.remote.model.manageCategoryDetailsResponseDto

import com.example.qrbnb_client.data.remote.service.manageCategoryDetails.ManageCategoryDetailsDataSource
import kotlinx.coroutines.delay
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.food

class ManageCategoryDetailsDataSourceImpl : ManageCategoryDetailsDataSource {
    override suspend fun getManageCategoryDetails(): List<ManageCategoryDetailsDto> {
        delay(500)

        // return dummy list of categories
        return listOf(
            ManageCategoryDetailsDto(
                id = "1",
                name = "Food",
                description = "All kinds of food items available",
                image = Res.drawable.food,
                isAvailable = true,
            ),
            ManageCategoryDetailsDto(
                id = "2",
                name = "Drinks",
                description = "Beverages and cold drinks",
                image = Res.drawable.food,
                isAvailable = false,
            ),
            ManageCategoryDetailsDto(
                id = "3",
                name = "Snacks",
                description = "Quick and light snacks for all times",
                image = Res.drawable.food,

                isAvailable = true,
            ),
            ManageCategoryDetailsDto(
                id = "4",
                name = "Groceries",
                description = "Daily household grocery items",
                image = Res.drawable.food,
                isAvailable = true,
            ),
            ManageCategoryDetailsDto(
                id = "5",
                name = "Electronics",
                description = "Gadgets and small electronic items",
                image = Res.drawable.food,
                isAvailable = false,
            ),
            ManageCategoryDetailsDto(
                id="6",
                name="Accessories",
                description = "get accessories here",
                image = Res.drawable.food,
                isAvailable = false,
            )
            
        )
    }
}

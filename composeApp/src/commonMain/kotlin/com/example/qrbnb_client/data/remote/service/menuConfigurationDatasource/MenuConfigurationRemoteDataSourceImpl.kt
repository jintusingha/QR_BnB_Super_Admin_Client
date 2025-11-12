package com.example.qrbnb_client.data.remote.service.menuConfigurationDatasource

import com.example.qrbnb_client.data.remote.model.menuConfigurationDto.MenuConfigItemDto
import com.example.qrbnb_client.data.remote.model.menuConfigurationDto.MenuConfigurationDto

class MenuConfigurationRemoteDataSourceImpl : MenuConfigurationRemoteDataSource {
    override suspend fun getMenuConfiguration(): MenuConfigurationDto =
        MenuConfigurationDto(
            configurations =
                listOf(
                    MenuConfigItemDto(
                        id = "1",
                        sectionTitle = "Badge Config",
                        title = "Badges",
                        description = "View, add, edit, or delete badges",
                        imageUrl = "https://example.com/icons/badge.png",
                    ),
                    MenuConfigItemDto(
                        id = "2",
                        sectionTitle = "Tag Config",
                        title = "Tags",
                        description = "List and add tags",
                        imageUrl = "https://example.com/icons/tag.png",
                    ),
                    MenuConfigItemDto(
                        id = "3",
                        sectionTitle = "Modifier Groups",
                        title = "Modifier Groups",
                        description = "View, edit, or create groups",
                        imageUrl = "https://example.com/icons/modifier.png",
                    ),
                    MenuConfigItemDto(
                        id = "4",
                        sectionTitle = "Variants",
                        title = "Variants",
                        description = "List and add variants",
                        imageUrl = "https://example.com/icons/variant.png",
                    ),
                ),
        )
}

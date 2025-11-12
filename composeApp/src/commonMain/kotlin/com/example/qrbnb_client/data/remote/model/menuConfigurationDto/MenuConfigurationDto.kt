package com.example.qrbnb_client.data.remote.model.menuConfigurationDto

import kotlinx.serialization.Serializable

@Serializable
data class MenuConfigurationDto(
    val configurations:List<MenuConfigItemDto>
)

@Serializable
data class MenuConfigItemDto(
    val id:String,
    val sectionTitle:String,
    val title:String,
    val description:String,
    val imageUrl:String?
)
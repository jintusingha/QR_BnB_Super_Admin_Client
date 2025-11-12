package com.example.qrbnb_client.domain.entity.menuConfigurationResponse

data class MenuConfiguration(
    val configurations:List<MenuConfigItem>
)

data class MenuConfigItem(
    val id:String,
    val sectionTitle:String,
    val title:String,
    val description:String,
    val imageUrl:String?
)
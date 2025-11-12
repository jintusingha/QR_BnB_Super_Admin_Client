package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.menuConfigurationDto.MenuConfigItemDto
import com.example.qrbnb_client.data.remote.model.menuConfigurationDto.MenuConfigurationDto
import com.example.qrbnb_client.domain.entity.menuConfigurationResponse.MenuConfigItem
import com.example.qrbnb_client.domain.entity.menuConfigurationResponse.MenuConfiguration

fun MenuConfigurationDto.toDomain(): MenuConfiguration{
    return MenuConfiguration(
        configurations=configurations.map{it.toDomain()}
    )
}

fun MenuConfigItemDto.toDomain(): MenuConfigItem {
    return MenuConfigItem(
        id=id,
        sectionTitle=sectionTitle,
        title=title,
        description=description,
        imageUrl=imageUrl
    )
}
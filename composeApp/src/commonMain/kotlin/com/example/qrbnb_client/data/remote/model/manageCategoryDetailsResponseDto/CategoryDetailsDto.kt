package com.example.qrbnb_client.data.remote.model.manageCategoryDetailsResponseDto

import org.jetbrains.compose.resources.DrawableResource

data class ManageCategoryDetailsDto(
    val id:String,
    val name:String,
    val description:String,
    val image: DrawableResource,
    val isAvailable:Boolean
)
package com.example.qrbnb_client.domain.entity.manageCategoryDetailsResponse

import org.jetbrains.compose.resources.DrawableResource

data class ManageCategoryDetails(
    val id:String,
    val name:String,
    val description:String,
    val image: DrawableResource,
    val isAvailable:Boolean,
    val price:String
)
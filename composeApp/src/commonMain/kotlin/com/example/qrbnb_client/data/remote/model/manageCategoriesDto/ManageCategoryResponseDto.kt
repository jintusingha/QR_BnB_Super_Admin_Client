package com.example.qrbnb_client.data.remote.model.manageCategoriesDto

import kotlinx.serialization.Serializable
@Serializable
data class ManageCategoryResponseDto(
    val success: Boolean,
    val message: String,
    val data: ManageCategoryListDto
)
@Serializable
data class ManageCategoryListDto(
    val categories: List<ManageCategoryDto>
)
@Serializable
data class ManageCategoryDto(
    val id: String,
    val name: String,
    val description: String,
    val createdAt: String
)
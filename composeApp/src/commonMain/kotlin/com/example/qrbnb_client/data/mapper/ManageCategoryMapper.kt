package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.manageCategoriesDto.ManageCategoryDto
import com.example.qrbnb_client.data.remote.model.manageCategoriesDto.ManageCategoryListDto
import com.example.qrbnb_client.domain.entity.manageCategoryResponse.Category

fun ManageCategoryDto.toDomain(): Category =
    Category(
        id = id,
        name = name,
        description = description,
        createdAt = createdAt,
    )

fun ManageCategoryListDto.toDomain(): List<Category> = categories.map { it.toDomain() }

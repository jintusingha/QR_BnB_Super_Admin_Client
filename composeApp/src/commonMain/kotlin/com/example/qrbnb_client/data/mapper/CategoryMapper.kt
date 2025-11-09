package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.addCategoryDto.CategoryDataDto
import com.example.qrbnb_client.domain.entity.addCategoryResponse.AddCategory
import com.example.qrbnb_client.domain.entity.manageCategoryResponse.Category

fun CategoryDataDto.toDomain(): AddCategory {
    return AddCategory(
        id=id,
        name=name,
        description=description,
        createdAt=createdAt
    )
}
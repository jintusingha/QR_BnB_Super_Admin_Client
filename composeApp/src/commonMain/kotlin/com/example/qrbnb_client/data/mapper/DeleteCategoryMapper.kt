package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.deleteCategoryResponseDto.DeleteCategoryResponseDto
import com.example.qrbnb_client.domain.entity.deleteCategoryResponse.DeleteCategoryResult

fun DeleteCategoryResponseDto.toDomain(): DeleteCategoryResult{
    return DeleteCategoryResult(
        success=success,
        message=message
    )
}
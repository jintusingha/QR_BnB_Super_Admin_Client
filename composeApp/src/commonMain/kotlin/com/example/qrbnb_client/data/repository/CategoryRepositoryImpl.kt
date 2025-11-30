package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomain
import com.example.qrbnb_client.data.remote.model.addCategoryDto.AddCategoryRequestDto
import com.example.qrbnb_client.data.remote.service.addCategoryDatasource.AddCategoryRemoteDataSource
import com.example.qrbnb_client.domain.entity.addCategoryResponse.AddCategory
import com.example.qrbnb_client.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val datasource: AddCategoryRemoteDataSource,
) : CategoryRepository {
    override suspend fun addCategory(
        name: String,
        description: String,
        topLevelCategory:String
    ): AddCategory {
        val request = AddCategoryRequestDto(
            name = name,
            topLevelCategory = topLevelCategory,
            description = description
        )
        val response=datasource.addCategory(request)
        return response.data.toDomain()

    }
}

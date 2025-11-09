package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomain
import com.example.qrbnb_client.data.remote.service.manageCategoryDataSource.ManageCategoryDataSource
import com.example.qrbnb_client.domain.entity.deleteCategoryResponse.DeleteCategoryResult
import com.example.qrbnb_client.domain.entity.manageCategoryResponse.Category
import com.example.qrbnb_client.domain.repository.ManageCategoryRepository

class ManageCategoryRepositoryImpl(
    private val datasource: ManageCategoryDataSource,
) : ManageCategoryRepository {
    override suspend fun getManageCategory(): List<Category> {
        val response = datasource.getManageCategory()
        return response.data.toDomain()
    }

    override suspend fun deleteCategory(id: String): DeleteCategoryResult {
        val response = datasource.deleteCategory(id)
        return response.toDomain()
    }
}

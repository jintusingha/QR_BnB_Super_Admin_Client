package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.deleteCategoryResponse.DeleteCategoryResult
import com.example.qrbnb_client.domain.entity.manageCategoryResponse.Category

interface ManageCategoryRepository {
    suspend fun getManageCategory(): List<Category>

    suspend fun deleteCategory(id: String): DeleteCategoryResult
}

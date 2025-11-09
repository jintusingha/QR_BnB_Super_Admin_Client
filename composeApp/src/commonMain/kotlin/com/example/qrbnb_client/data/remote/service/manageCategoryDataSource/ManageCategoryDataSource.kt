package com.example.qrbnb_client.data.remote.service.manageCategoryDataSource

import com.example.qrbnb_client.data.remote.model.deleteCategoryResponseDto.DeleteCategoryResponseDto
import com.example.qrbnb_client.data.remote.model.manageCategoriesDto.ManageCategoryResponseDto

interface ManageCategoryDataSource{
    suspend fun getManageCategory(): ManageCategoryResponseDto
    suspend fun deleteCategory(id:String): DeleteCategoryResponseDto
}
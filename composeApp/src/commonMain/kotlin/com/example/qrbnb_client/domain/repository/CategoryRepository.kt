package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.addCategoryResponse.AddCategory

interface CategoryRepository {
    suspend fun addCategory(name:String,description:String,topLevelCategory:String): AddCategory
}
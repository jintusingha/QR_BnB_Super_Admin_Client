package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.addCategoryResponse.AddCategory
import com.example.qrbnb_client.domain.repository.CategoryRepository

class AddCategoryUseCase (
    private val repository: CategoryRepository
){
    suspend operator fun invoke(name: String, topLevelCategory: String, description: String): AddCategory
    {
        return repository.addCategory(name,topLevelCategory,description)
    }
}
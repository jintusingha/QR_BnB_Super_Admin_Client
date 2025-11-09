package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.manageCategoryResponse.Category
import com.example.qrbnb_client.domain.repository.ManageCategoryRepository

class ManageCategoryUseCase (private val repository: ManageCategoryRepository){
    suspend operator fun invoke():List<Category>{
        return repository.getManageCategory()
    }
}
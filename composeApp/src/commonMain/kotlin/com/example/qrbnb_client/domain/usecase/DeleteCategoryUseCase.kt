package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.deleteCategoryResponse.DeleteCategoryResult
import com.example.qrbnb_client.domain.repository.ManageCategoryRepository

class DeleteCategoryUseCase(
    private val repository: ManageCategoryRepository,
) {
    suspend operator fun invoke(id: String): DeleteCategoryResult = repository.deleteCategory(id)
}

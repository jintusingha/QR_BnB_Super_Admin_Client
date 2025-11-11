package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.manageCategoryDetailsResponse.ManageCategoryDetails
import com.example.qrbnb_client.domain.repository.ManageCategoryDetailsRepository

class GetManageCategoryDetailUseCase (
    private val repository: ManageCategoryDetailsRepository
){
    suspend operator fun invoke():List<ManageCategoryDetails>{
        return repository.getManageCategoryDetailsRepository()
    }
}
package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomain
import com.example.qrbnb_client.data.remote.service.manageCategoryDetails.ManageCategoryDetailsDataSource
import com.example.qrbnb_client.domain.entity.manageCategoryDetailsResponse.ManageCategoryDetails
import com.example.qrbnb_client.domain.repository.ManageCategoryDetailsRepository

class ManageCategoryDetailsRepositoryImpl (private val dataSource: ManageCategoryDetailsDataSource): ManageCategoryDetailsRepository{
    override suspend fun getManageCategoryDetailsRepository(): List<ManageCategoryDetails> {
        return dataSource.getManageCategoryDetails().map { it.toDomain() }
    }

}
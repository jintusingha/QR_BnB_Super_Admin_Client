package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.data.remote.model.manageCategoryDetailsResponseDto.ManageCategoryDetailsDto
import com.example.qrbnb_client.domain.entity.manageCategoryDetailsResponse.ManageCategoryDetails

interface ManageCategoryDetailsRepository {
    suspend fun getManageCategoryDetailsRepository():List<ManageCategoryDetails>
}
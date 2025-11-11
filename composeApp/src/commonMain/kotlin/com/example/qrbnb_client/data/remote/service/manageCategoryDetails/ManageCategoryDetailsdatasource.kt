package com.example.qrbnb_client.data.remote.service.manageCategoryDetails

//import com.example.qrbnb_client.data.remote.model.manageCategoryDetailsResponseDto.CategoryDetailsDto
import com.example.qrbnb_client.data.remote.model.manageCategoryDetailsResponseDto.ManageCategoryDetailsDto

interface ManageCategoryDetailsDataSource {
    suspend fun getManageCategoryDetails(): List<ManageCategoryDetailsDto>
}
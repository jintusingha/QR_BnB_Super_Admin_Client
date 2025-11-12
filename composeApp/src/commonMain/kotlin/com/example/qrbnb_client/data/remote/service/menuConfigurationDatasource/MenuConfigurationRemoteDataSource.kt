package com.example.qrbnb_client.data.remote.service.menuConfigurationDatasource

import com.example.qrbnb_client.data.remote.model.menuConfigurationDto.MenuConfigurationDto

interface MenuConfigurationRemoteDataSource {
    suspend fun getMenuConfiguration(): MenuConfigurationDto
}

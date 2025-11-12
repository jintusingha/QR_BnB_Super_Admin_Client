package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomain
import com.example.qrbnb_client.data.remote.service.menuConfigurationDatasource.MenuConfigurationRemoteDataSource
import com.example.qrbnb_client.domain.entity.menuConfigurationResponse.MenuConfiguration
import com.example.qrbnb_client.domain.repository.MenuConfigurationRepository

class MenuConfigurationRepositoryImpl (private val datasource: MenuConfigurationRemoteDataSource):
    MenuConfigurationRepository{
    override suspend fun getMenuConfiguration(): MenuConfiguration {
        val response=datasource.getMenuConfiguration()
        return response.toDomain()

    }

}
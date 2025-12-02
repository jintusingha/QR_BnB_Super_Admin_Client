package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntity
import com.example.qrbnb_client.data.remote.service.menuDataSource.MenuDataSource
import com.example.qrbnb_client.domain.entity.menuEntity.MenuEntity
import com.example.qrbnb_client.domain.repository.MenuRepository

class MenuRepositoryImpl (
    private val dataSource: MenuDataSource
): MenuRepository{
    override suspend fun getMenu(): MenuEntity {
        val dto=dataSource.getMenu()
        return dto.toEntity()
    }
}
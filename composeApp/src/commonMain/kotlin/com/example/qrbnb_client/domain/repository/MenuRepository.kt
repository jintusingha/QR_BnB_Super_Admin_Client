package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.menuEntity.MenuEntity

interface MenuRepository{
    suspend fun getMenu(): MenuEntity
}
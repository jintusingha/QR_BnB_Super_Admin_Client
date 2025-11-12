package com.example.qrbnb_client.domain.repository

import com.example.qrbnb_client.domain.entity.menuConfigurationResponse.MenuConfiguration

interface MenuConfigurationRepository {
    suspend fun getMenuConfiguration(): MenuConfiguration
}
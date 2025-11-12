package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.menuConfigurationResponse.MenuConfiguration
import com.example.qrbnb_client.domain.repository.MenuConfigurationRepository

class MenuConfigurationUseCase (private val repository: MenuConfigurationRepository){
    suspend operator fun invoke(): MenuConfiguration{
        return repository.getMenuConfiguration()
    }
}
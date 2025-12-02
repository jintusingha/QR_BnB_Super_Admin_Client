package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.menuEntity.MenuEntity
import com.example.qrbnb_client.domain.repository.MenuRepository

class GetMenuUseCase (
    private val repository: MenuRepository
){
    suspend operator fun invoke(): MenuEntity{
        return repository.getMenu()
    }
}
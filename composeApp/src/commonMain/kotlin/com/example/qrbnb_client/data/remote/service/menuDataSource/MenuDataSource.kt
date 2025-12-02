package com.example.qrbnb_client.data.remote.service.menuDataSource

import com.example.qrbnb_client.data.remote.model.MenuReponseDto.MenuResponseDto

interface MenuDataSource {
    suspend fun getMenu(): MenuResponseDto
}
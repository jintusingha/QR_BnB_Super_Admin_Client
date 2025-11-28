package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntity
import com.example.qrbnb_client.data.remote.service.seatingAreasRemoteDataSource.SeatingAreasRemoteDataSource
import com.example.qrbnb_client.domain.entity.seatingAreasEntity.SeatingAreasEntity
import com.example.qrbnb_client.domain.repository.SeatingAreasRepository

class SeatingAreasRepositoryImpl(
    private val remote: SeatingAreasRemoteDataSource,
) : SeatingAreasRepository {
    override suspend fun getSeatingAreas(): SeatingAreasEntity {
        val dto = remote.fetchSeatingAreas()
        return dto.toEntity()
    }
}

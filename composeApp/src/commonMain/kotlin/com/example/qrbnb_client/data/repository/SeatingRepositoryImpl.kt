package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntityList
import com.example.qrbnb_client.data.remote.service.seatingListDataSource.SeatingDataSource
import com.example.qrbnb_client.domain.entity.seatingEntity.SeatingEntity
import com.example.qrbnb_client.domain.repository.SeatingRepository

class SeatingRepositoryImpl(
    private val dataSource: SeatingDataSource
) : SeatingRepository {

    override suspend fun getSeatingList(): List<SeatingEntity> {
        val response = dataSource.getSeatingList()

        return response.data.toEntityList()
    }
}
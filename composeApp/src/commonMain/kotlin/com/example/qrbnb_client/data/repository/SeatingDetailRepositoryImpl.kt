package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntity
import com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource.SeatingDetailRemoteDataSource
import com.example.qrbnb_client.domain.entity.generateQrEntity.SeatingDetailEntity
import com.example.qrbnb_client.domain.repository.SeatingDetailRepository

class SeatingDetailRepositoryImpl(
    private val remote: SeatingDetailRemoteDataSource
) : SeatingDetailRepository {

    override suspend fun getSeatingDetail(seatingId: String): SeatingDetailEntity {
        return remote.getSeatingDetail(seatingId).toEntity()
    }
}
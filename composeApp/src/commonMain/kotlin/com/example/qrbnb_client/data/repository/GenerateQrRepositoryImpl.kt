package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntity
import com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource.GenerateQrRemoteDataSource
import com.example.qrbnb_client.domain.entity.generateQrEntity.GenerateQrEntity
import com.example.qrbnb_client.domain.repository.GenerateQrRepository

class GenerateQrRepositoryImpl(
    private val remote: GenerateQrRemoteDataSource,
) : GenerateQrRepository {
    override suspend fun generateQr(seatingId: String): GenerateQrEntity = remote.generateQr(seatingId).toEntity()
}

package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomain
import com.example.qrbnb_client.data.remote.service.qrCodeDataSource.QrCodesDataSource
import com.example.qrbnb_client.domain.entity.qrCodesResponse.QrCodesResponse
import com.example.qrbnb_client.domain.repository.QrCodesRepository

class QrCodesRepositoryImpl(
    private val dataSource: QrCodesDataSource
) : QrCodesRepository {

    override suspend fun getQrCodes(): QrCodesResponse {
        return dataSource.getQrCodes().toDomain()
    }
}
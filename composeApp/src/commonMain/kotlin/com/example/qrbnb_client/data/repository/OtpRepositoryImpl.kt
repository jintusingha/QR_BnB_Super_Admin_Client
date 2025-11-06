package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toDomain
import com.example.qrbnb_client.data.remote.service.OtpDataSource.OtpDataSource

import com.example.qrbnb_client.domain.entity.otpResponse.OtpResponse
import com.example.qrbnb_client.domain.repository.OtpRepository

class OtpRepositoryImpl (private val dataSource: OtpDataSource): OtpRepository {
    override suspend fun sendOtp(phoneNumber: String): OtpResponse {
        val response=dataSource.sendOtp(phoneNumber)
        return response.toDomain()

    }
}
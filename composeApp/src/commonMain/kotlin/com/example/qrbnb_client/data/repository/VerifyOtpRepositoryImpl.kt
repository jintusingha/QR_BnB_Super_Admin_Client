package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.mapper.toEntity
import com.example.qrbnb_client.data.remote.model.verifyotpRequestDto.VerifyOtpRequestDto
import com.example.qrbnb_client.data.remote.service.verifyOtpRemoteDataSource.VerifyOtpRemoteDataSource
import com.example.qrbnb_client.data.remote.service.verifyOtpRemoteDataSource.VerifyOtpRemoteDataSourceImpl
import com.example.qrbnb_client.domain.entity.verifyotpresponse.VerifyOtpEntity
import com.example.qrbnb_client.domain.repository.VerifyOtpRepository


class VerifyOtpRepositoryImpl (private val remoteDataSource: VerifyOtpRemoteDataSource):
    VerifyOtpRepository {
    override suspend fun verifyOtp(
        phoneNumber: String,
        otp: String
    ): VerifyOtpEntity {
        val request= VerifyOtpRequestDto(phoneNumber,otp)
        val response=remoteDataSource.verifyOtp(request)
        return response.toEntity()

    }
}
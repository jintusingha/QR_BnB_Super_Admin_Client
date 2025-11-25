package com.example.qrbnb_client.data.repository

import com.example.qrbnb_client.data.remote.service.imageUploadRemoteDataSource.UploadRemoteDataSource
import com.example.qrbnb_client.domain.repository.UploadRepository

class UploadRepositoryImpl(
    private val remoteDataSource: UploadRemoteDataSource
) : UploadRepository {

    override suspend fun uploadImage(
        fileName: String,
        fileBytes: ByteArray,
        mimeType: String
    ): String {
        return remoteDataSource.uploadImage(fileName, fileBytes, mimeType)
    }
}
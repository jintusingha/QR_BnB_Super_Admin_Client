package com.example.qrbnb_client.data.remote.service.imageUploadRemoteDataSource

interface UploadRemoteDataSource {
    suspend fun uploadImage(
        fileName: String,
        fileBytes: ByteArray,
        mimeType: String
    ): String
}
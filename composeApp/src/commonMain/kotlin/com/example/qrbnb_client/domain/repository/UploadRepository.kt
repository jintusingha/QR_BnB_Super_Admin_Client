package com.example.qrbnb_client.domain.repository

interface UploadRepository {
    suspend fun uploadImage(
        fileName: String,
        fileBytes: ByteArray,
        mimeType: String
    ): String
}
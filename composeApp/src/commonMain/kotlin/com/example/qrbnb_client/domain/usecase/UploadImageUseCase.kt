package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.data.UriHelper
import com.example.qrbnb_client.domain.repository.UploadRepository

class UploadImageUseCase(
    private val repository: UploadRepository,
    private val uriHelper: UriHelper,
) {
    suspend operator fun invoke(uriString: String): String {
        val fileName = uriHelper.getFileNameFromUri(uriString)
        val fileBytes = uriHelper.getBytesFromUri(uriString)

        val mimeType = uriHelper.getMimeType(uriString)

        return repository.uploadImage(fileName, fileBytes, mimeType)
    }
}

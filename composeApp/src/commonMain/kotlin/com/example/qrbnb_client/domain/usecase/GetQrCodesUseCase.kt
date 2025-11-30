package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.repository.QrCodesRepository

class GetQrCodesUseCase(
    private val repository: QrCodesRepository
) {
    suspend operator fun invoke() = repository.getQrCodes()
}
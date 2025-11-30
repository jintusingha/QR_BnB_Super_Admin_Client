package com.example.qrbnb_client.data.remote.service.generateQrRemoteDataSource

import com.example.qrbnb_client.data.remote.model.generateQrResponseDto.QrSettingsDto
import com.example.qrbnb_client.data.remote.model.generateQrResponseDto.SeatingDetailResponseDto

class SeatingDetailRemoteDataSourceDummyImpl : SeatingDetailRemoteDataSource {

    override suspend fun getSeatingDetail(seatingId: String): SeatingDetailResponseDto {

        return SeatingDetailResponseDto(
            id = seatingId,
            type = "table",
            name = "Table 5",
            description = "Dining Room",
            imageUrl = "https://picsum.photos/200/200",
            isActive = true,
            qrCodeUrl = null,
            qrSettings = QrSettingsDto(
                includeRoomName = true,
                layoutStyle = "Rounded",
                size = "Medium"
            )
        )
    }
}

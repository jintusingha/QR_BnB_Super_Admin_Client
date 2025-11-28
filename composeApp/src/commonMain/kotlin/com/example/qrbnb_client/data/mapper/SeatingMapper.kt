package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.seatingAreasDto.SeatingAreasResponseDto
import com.example.qrbnb_client.domain.entity.seatingAreasEntity.RoomEntity
import com.example.qrbnb_client.domain.entity.seatingAreasEntity.SeatingAreasEntity
import com.example.qrbnb_client.domain.entity.seatingAreasEntity.TableEntity

fun SeatingAreasResponseDto.toEntity(): SeatingAreasEntity {
    val rooms = mutableListOf<RoomEntity>()
    val tables = mutableListOf<TableEntity>()

    data.forEach { dto ->
        when (dto.type.lowercase()) {
            "room" ->
                rooms.add(
                    RoomEntity(
                        id = dto.id,
                        name = dto.name,
                        description = dto.description,
                        capacity = dto.capacity,
                        isActive = dto.isActive,
                        imageUrl = dto.imageUrl,
                        qrCodeUrl = dto.qrCodeUrl,
                    ),
                )
            "table" ->
                tables.add(
                    TableEntity(
                        id = dto.id,
                        name = dto.name,
                        description = dto.description,
                        capacity = dto.capacity,
                        isActive = dto.isActive,
                        imageUrl = dto.imageUrl,
                        qrCodeUrl = dto.qrCodeUrl,
                    ),
                )
        }
    }
    return SeatingAreasEntity(
        rooms = rooms,
        tables = tables,
    )
}

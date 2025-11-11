package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.manageCategoryDetailsResponseDto.ManageCategoryDetailsDto
import com.example.qrbnb_client.domain.entity.manageCategoryDetailsResponse.ManageCategoryDetails

fun ManageCategoryDetailsDto.toDomain(): ManageCategoryDetails{
    return ManageCategoryDetails(
        id=id,
        name=name,
        description=description,
        image=image,
        isAvailable=isAvailable
    )
}
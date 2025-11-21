package com.example.qrbnb_client.data.remote.service.variantRemoteDatasource

import com.example.qrbnb_client.data.remote.model.variantsDto.VariantDto
import com.example.qrbnb_client.data.remote.model.variantsDto.VariantsResponseDto

class VariantRemoteDataSourceImpl : VariantRemoteDataSource {
    override suspend fun getVariant(): VariantsResponseDto =
        VariantsResponseDto(
            success = true,
            data =
                listOf(
                    VariantDto("1", "Size", 2),
                    VariantDto("2", "Crust", 3),
                    VariantDto("3", "Toppings", 4),
                ),
        )
}

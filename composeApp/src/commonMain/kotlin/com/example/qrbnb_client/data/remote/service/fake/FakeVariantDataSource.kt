package com.example.qrbnb_client.data.remote.service.fake

data class FakeVariantDto(val id: String, val name: String)

object FakeVariantDataSource {
    fun getVariants(): List<FakeVariantDto> = listOf(
        FakeVariantDto("v1", "Size"),
        FakeVariantDto("v2", "Flavor")
    )
}
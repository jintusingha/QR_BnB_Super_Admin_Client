package com.example.qrbnb_client.data.remote.service.fake

data class FakeCategoryDto(val id: String, val name: String)

object FakeCategoryDataSource {
    fun getCategories(): List<FakeCategoryDto> = listOf(
        FakeCategoryDto("c1", "Beverages"),
        FakeCategoryDto("c2", "Main Course"),
        FakeCategoryDto("c3", "Desserts"),
        FakeCategoryDto("c4", "Snacks")
    )
}
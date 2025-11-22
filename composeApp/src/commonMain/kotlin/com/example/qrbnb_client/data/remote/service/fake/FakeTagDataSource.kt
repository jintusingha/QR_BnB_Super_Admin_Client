package com.example.qrbnb_client.data.remote.service.fake

data class FakeTagDto(val id: String, val name: String)

object FakeTagDataSource {
    fun getTags(): List<FakeTagDto> = listOf(
        FakeTagDto("t1", "Tag 1"),
        FakeTagDto("t2", "Tag 2"),
        FakeTagDto("t3", "Tag 3")
    )
}
package com.example.qrbnb_client.data.remote.service.fake

data class FakeBadgeDto(val id: String, val name: String)

object FakeBadgeDataSource {
    fun getBadges(): List<FakeBadgeDto> = listOf(
        FakeBadgeDto("b1", "Badge 1"),
        FakeBadgeDto("b2", "Badge 2"),
        FakeBadgeDto("b3", "Badge 3")
    )
}
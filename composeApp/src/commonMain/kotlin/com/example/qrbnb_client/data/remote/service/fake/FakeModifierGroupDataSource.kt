package com.example.qrbnb_client.data.remote.service.fake

data class FakeModifierGroupDto(val id: String, val name: String)

object FakeModifierGroupDataSource {
    fun getModifierGroups(): List<FakeModifierGroupDto> = listOf(
        FakeModifierGroupDto("mg1", "Group 1"),
        FakeModifierGroupDto("mg2", "Group 2"),
        FakeModifierGroupDto("mg3", "Group 3")
    )
}
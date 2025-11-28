package com.example.qrbnb_client.data.mapper

import com.example.qrbnb_client.data.remote.model.createSeatingDto.CreateSeatingRequestDto
import com.example.qrbnb_client.domain.entity.createSeatingEntity.CreateSeatingEntity

fun CreateSeatingEntity.toRequestDto()= CreateSeatingRequestDto(
    type=this.type,
    name=this.name,
    description=this.description
)
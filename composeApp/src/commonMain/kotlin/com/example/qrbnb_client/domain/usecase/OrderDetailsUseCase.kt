package com.example.qrbnb_client.domain.usecase

import com.example.qrbnb_client.domain.entity.orderDetailsResponse.OrderDetailsEntity
import com.example.qrbnb_client.domain.repository.OrderDetailsRepository

class OrderDetailsUseCase (private val repository: OrderDetailsRepository){
    suspend operator fun invoke(orderId:String): OrderDetailsEntity{
        return repository.getOrderDetails(orderId)
    }
}
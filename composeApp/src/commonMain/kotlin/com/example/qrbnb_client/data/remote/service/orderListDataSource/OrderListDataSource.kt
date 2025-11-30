package com.example.qrbnb_client.data.remote.service.orderListDataSource

import com.example.qrbnb_client.data.remote.model.orderListDto.OrderListResponseDto

interface OrderListDataSource{
    suspend fun getOrderListData(clientId:String,status:String?=null): OrderListResponseDto
}
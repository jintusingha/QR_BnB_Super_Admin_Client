package com.example.qrbnb_client.navigation

import com.example.qrbnb_client.data.TokenStorage

class AuthStatusChecker (private val tokenStorage: TokenStorage){
    fun getStartDestination():String{
        val accessToken=tokenStorage.getAccessToken()
        return if(!accessToken.isNullOrEmpty()){
            ScreenRoute.ClientDashboard.route
        }else{
            ScreenRoute.Login.route
        }
    }
}
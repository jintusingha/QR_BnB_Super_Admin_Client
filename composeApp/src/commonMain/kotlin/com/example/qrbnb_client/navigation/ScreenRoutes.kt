package com.example.qrbnb_client.navigation

sealed class ScreenRoute(
    val route: String,
) {
    object Login : ScreenRoute("login_route")

    data class VerifyOtp(
        val phoneNumber: String,
    ) : ScreenRoute("verifyOtp/$phoneNumber") {
        companion object {
            const val ROUTE_WITH_ARGS = "verifyOtp/{phoneNumber}"
        }
    }
}

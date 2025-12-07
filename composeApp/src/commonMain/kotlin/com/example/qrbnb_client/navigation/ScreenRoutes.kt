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

    object ClientDashboard : ScreenRoute("client_dashboard_route")

    object ManageCategories : ScreenRoute("manage_categories_route")

    object AddCategory : ScreenRoute("add_category_route")

    object ManageCategoryDetail : ScreenRoute("manage_category_detail_route/{categoryId}") {
        fun createRoute(categoryId: String) = "manage_category_detail_route/$categoryId"
    }

    object AddItem : ScreenRoute("add_item_route")

    object MenuConfiguration : ScreenRoute("menu_configuration_route")

    object AddBadge : ScreenRoute("add_badge_route")

    object AddTag : ScreenRoute("add_tag_route")

    object ManageModifierGroups : ScreenRoute("manage_modifier_groups_route")

    object AddModifierGroup : ScreenRoute("add_modifier_group_route")

    object ManageVariants : ScreenRoute("manage_variants_route")

    object AddVariant : ScreenRoute("add_variant_route")

    object Orders : ScreenRoute("orders_route")

    object OrderDetails : ScreenRoute("order_details/{orderId}") {
        fun createRoute(orderId: String) = "order_details/$orderId"
    }
    object SeatingSelection : ScreenRoute("seating_selection_route")

}

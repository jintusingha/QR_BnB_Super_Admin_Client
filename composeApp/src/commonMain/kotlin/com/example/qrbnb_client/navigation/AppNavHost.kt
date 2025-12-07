import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.qrbnb_client.navigation.AuthStatusChecker
import com.example.qrbnb_client.navigation.ScreenRoute
import com.example.qrbnb_client.presentation.screen.AddItemScreen
import com.example.qrbnb_client.presentation.screen.AddModifierGroupScreen
import com.example.qrbnb_client.presentation.screen.AddVariantScreen
import com.example.qrbnb_client.presentation.screen.DynamicAddItemScreen
import com.example.qrbnb_client.presentation.screen.ManualOrderScreen
import com.example.qrbnb_client.presentation.screen.MenuConfigurationScreen
import com.example.qrbnb_client.presentation.screen.OrdersScreen
import com.example.qrbnb_client.presentation.screen.OtpScreen
import com.example.qrbnb_client.presentation.screen.OtpVerificationScreen
import com.example.qrbnb_client.presentation.screen.addBadgeScreen.AddBadgeScreen
import com.example.qrbnb_client.presentation.screen.addCategoryScreen.AddCategoryScreen
import com.example.qrbnb_client.presentation.screen.editTagScreen.EditTagScreen
import com.example.qrbnb_client.presentation.screen.manageCategoryDetailsScreen.ManageCategoryDetailScreen
import com.example.qrbnb_client.presentation.screen.manageCategoryScreen.ManageCategoriesScreen
import com.example.qrbnb_client.presentation.screen.modifierGroupsScreen.ModifierGroupsScreen
import com.example.qrbnb_client.presentation.screen.orderDetailsScreen.OrderDetailsScreen
import com.example.qrbnb_client.presentation.screen.ordersListScreen.OrdersListScreen
import com.example.qrbnb_client.presentation.screen.seatingSelectionScreen.SeatingSelectionScreen
import com.example.qrbnb_client.presentation.screen.tagScreen.TagsScreen
import com.example.qrbnb_client.presentation.screen.variantsScreen.VariantsScreen
import org.koin.compose.koinInject

@Composable
fun AppNavHost(authStatusChecker: AuthStatusChecker) {
    val initialRoute = authStatusChecker.getStartDestination()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = initialRoute,
    ) {
        composable(ScreenRoute.Login.route) {
            OtpScreen(
                onOtpSentSuccess = { phoneNumber ->

                    val destination = ScreenRoute.VerifyOtp(phoneNumber)

                    navController.navigate(destination.route)
                },
            )
        }

        composable(
            route = ScreenRoute.VerifyOtp.ROUTE_WITH_ARGS,
            arguments =
                listOf(
                    navArgument("phoneNumber") { type = NavType.StringType },
                ),
        ) { backStackEntry ->

            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""

            OtpVerificationScreen(phoneNumber = phoneNumber, onNavigateBack = { navController.popBackStack() }, onVerificationSuccess = {
                navController.navigate(ScreenRoute.ClientDashboard.route) {
                    popUpTo(ScreenRoute.Login.route) { inclusive = true }
                }
            })
        }
        composable(ScreenRoute.ClientDashboard.route) {
            ClientDashboardScreen(
                onOrdersClick = {
                    navController.navigate(ScreenRoute.Orders.route)
                },
                onMenuManagementClick = { endpoint ->
                    when (endpoint) {
                        "/client/menu/categories" ->
                            navController.navigate(ScreenRoute.ManageCategories.route)

                        "/client/menu/settings" ->
                            navController.navigate(ScreenRoute.MenuConfiguration.route)
                    }
                },
                onFabClick = {
                    navController.navigate(ScreenRoute.SeatingSelection.route)
                },
            )
        }

        composable(ScreenRoute.ManageCategories.route) {
            ManageCategoriesScreen(
                onAddCategoryClick = {
                    navController.navigate(ScreenRoute.AddCategory.route)
                },
                onCategoryClick = { categoryId ->
                    navController.navigate(ScreenRoute.ManageCategoryDetail.createRoute(categoryId))
                },
            )
        }

        composable(ScreenRoute.AddCategory.route) {
            AddCategoryScreen(
                onNavigateBack = { navController.popBackStack() },
                onCategoryAdded = { navController.popBackStack() },
            )
        }
        composable(
            route = ScreenRoute.ManageCategoryDetail.route,
            arguments =
                listOf(
                    navArgument("categoryId") { type = NavType.StringType },
                ),
        ) { backStackEntry ->

            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""

            ManageCategoryDetailScreen(
                categoryId = categoryId,
                onNavigateBack = { navController.popBackStack() },
                onAddItemClick = {
                    navController.navigate(ScreenRoute.AddItem.route)
                },
            )
        }

        composable(ScreenRoute.AddItem.route) {
            DynamicAddItemScreen(
                onBack = { navController.popBackStack() },
            )
        }
        composable(ScreenRoute.MenuConfiguration.route) {
            MenuConfigurationScreen(
                onNavigateBack = { navController.popBackStack() },
                onManageClick = { id ->
                    when (id) {
                        "3" -> navController.navigate(ScreenRoute.ManageModifierGroups.route)
                        "4" -> navController.navigate(ScreenRoute.ManageVariants.route)
                    }
                },
                onAddClick = { id ->
                    when (id) {
                        "1" -> navController.navigate(ScreenRoute.AddBadge.route)
                        "2" -> navController.navigate(ScreenRoute.AddTag.route)
                        "3" -> navController.navigate(ScreenRoute.AddModifierGroup.route)
                        "4" -> navController.navigate(ScreenRoute.AddVariant.route)
                    }
                },
            )
        }
        composable(ScreenRoute.AddBadge.route) {
            AddBadgeScreen(
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(ScreenRoute.AddTag.route) {
            TagsScreen(
                onBackClick = { navController.popBackStack() },
                onTagClick = { tag ->

                    navController.navigate(
                        ScreenRoute.EditTag.createRoute(
                            tag.id,
                            tag.name,
                        ),
                    )
                },
            )
        }

        composable(ScreenRoute.ManageModifierGroups.route) {
            ModifierGroupsScreen(
                onBackClick = { navController.popBackStack() },
                onAddGroupClick = { navController.navigate(ScreenRoute.AddModifierGroup.route) },
                onEditGroupClick = { groupId -> },
            )
        }
        composable(ScreenRoute.AddModifierGroup.route) {
            AddModifierGroupScreen(
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(ScreenRoute.ManageVariants.route) {
            VariantsScreen(
                onBackClick = { navController.popBackStack() },
                onAddGroupClick = { navController.navigate(ScreenRoute.AddVariant.route) },
                onEditGroupClick = { variantId ->
                },
            )
        }
        composable(ScreenRoute.AddVariant.route) {
            AddVariantScreen(
                onBack = { navController.popBackStack() },
                onSuccess = { navController.popBackStack() },
            )
        }
        composable(ScreenRoute.Orders.route) {
            OrdersListScreen(
                clientId = "5",
                onBackClick = { navController.popBackStack() },
                onOrderClick = { orderId ->
                    navController.navigate(ScreenRoute.OrderDetails.createRoute(orderId))
                },
            )
        }
        composable(
            route = ScreenRoute.OrderDetails.route,
            arguments =
                listOf(
                    navArgument("orderId") { type = NavType.StringType },
                ),
        ) { backStackEntry ->

            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""

            OrderDetailsScreen(
                orderId = orderId,
                onBackClick = { navController.popBackStack() },
            )
        }
        composable(ScreenRoute.SeatingSelection.route) {
            SeatingSelectionScreen(
                onCloseClick = { navController.popBackStack() },
                onNextClick = {
                    navController.navigate(ScreenRoute.ManualOrder.route)
                },
                onSeatingSelected = { seating ->
                },
            )
        }
        composable(ScreenRoute.ManualOrder.route) {
            ManualOrderScreen(
                onCloseClick = { navController.popBackStack() },
                onProceedToCheckout = {
                    navController.navigate(ScreenRoute.Checkout.route)
                },
            )
        }
        composable(ScreenRoute.Checkout.route) {
            CheckoutScreen(
                onCloseClick = { navController.popBackStack() },
                onOrderSuccess = { orderId, orderNumber ->

                    navController.navigate(ScreenRoute.ClientDashboard.route) {
                        popUpTo(ScreenRoute.ClientDashboard.route)
                    }
                },
            )
        }
        composable(
            route = ScreenRoute.EditTag.route,
            arguments =
                listOf(
                    navArgument("tagId") { type = NavType.StringType },
                    navArgument("tagName") { type = NavType.StringType },
                ),
        ) { backStackEntry ->

            val tagId = backStackEntry.arguments?.getString("tagId") ?: ""
            val tagName = backStackEntry.arguments?.getString("tagName") ?: ""

            EditTagScreen(
                tagId = tagId,
                tagName = tagName,
                onBack = { navController.popBackStack() },
                onCancel = { navController.popBackStack() },
                onDelete = { navController.popBackStack() },
            )
        }
    }
}

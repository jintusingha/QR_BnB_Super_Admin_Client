import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.qrbnb_client.navigation.ScreenRoute
import com.example.qrbnb_client.presentation.screen.ClientDashboardScreen.ClientDashboardScreen
import com.example.qrbnb_client.presentation.screen.OtpScreen
import com.example.qrbnb_client.presentation.screen.OtpVerificationScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Login.route,
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

            OtpVerificationScreen(phoneNumber = phoneNumber, onNavigateBack = {navController.popBackStack()}, onVerificationSuccess = {
                navController.navigate(ScreenRoute.ClientDashboard.route){
                    popUpTo(ScreenRoute.Login.route){inclusive=true}
                }
            })
        }
        composable(ScreenRoute.ClientDashboard.route){
            ClientDashboardScreen()
        }
    }
}

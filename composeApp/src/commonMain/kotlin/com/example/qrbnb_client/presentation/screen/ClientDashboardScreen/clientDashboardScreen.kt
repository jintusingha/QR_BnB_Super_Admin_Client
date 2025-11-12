

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.screen.ClientDashboardScreen.ClientDashboardContent
import com.example.qrbnb_client.presentation.screen.ClientDashboardScreen.ClientDashboardError
import com.example.qrbnb_client.presentation.state.ClientDashboardState
import com.example.qrbnb_client.presentation.viewmodel.ClientDashboardViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.hamburger
import qr_bnb_client.composeapp.generated.resources.infoicon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientDashboardScreen(viewModel: ClientDashboardViewModel = koinInject()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getDashboardData()
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomTopAppBar(
                title = "QRBnB",
                navigationIcon = {
                    IconButton({}) {
                        Icon(
                            painter = painterResource(Res.drawable.hamburger),
                            contentDescription = "hamburgericon",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.infoicon),
                            contentDescription = "infoicon",
                            modifier = Modifier.size(24.dp),
                        )
                    }
                },
            )
        },floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle add order action */ },
                containerColor = Color(0xFFFF5252),
                contentColor = Color.White,
                shape = RoundedCornerShape(28.dp),
                modifier =
                    Modifier
                        .width(64.dp)
                        .height(56.dp),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, end = 24.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Order",
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        },
    ) { paddingValues ->
        when (state) {
            is ClientDashboardState.Loading -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Loading Dashboard...")
                    }
                }
            }

            is ClientDashboardState.Error -> {
                ClientDashboardError(
                    errorMessage = (state as ClientDashboardState.Error).message,
                    onRetry = viewModel::getDashboardData,
                    paddingValues = paddingValues
                )
            }

            is ClientDashboardState.Success -> {
                ClientDashboardContent(
                    data = (state as ClientDashboardState.Success).data,
                    paddingValues = paddingValues
                )
            }
        }
    }
}
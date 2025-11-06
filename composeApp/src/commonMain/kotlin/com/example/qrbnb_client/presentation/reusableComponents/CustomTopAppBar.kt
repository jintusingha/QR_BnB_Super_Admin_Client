package com.example.qrbnb_client.presentation.reusableComponents

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.qrbnb_client.ui.style_18_23_700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = style_18_23_700(),
            )
        },
        navigationIcon = { navigationIcon?.invoke() },
        actions = { actions?.invoke() },
        colors =
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black,
                actionIconContentColor = Color.Black,
            ),
    )
}

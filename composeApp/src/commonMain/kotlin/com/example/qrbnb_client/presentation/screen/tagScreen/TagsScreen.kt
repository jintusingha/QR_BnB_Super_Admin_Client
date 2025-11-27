package com.example.qrbnb_client.presentation.screen.tagScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.domain.entity.tagResponse.TagEntity
import com.example.qrbnb_client.presentation.reusableComponents.CustomTopAppBar
import com.example.qrbnb_client.presentation.state.TagUiState
import com.example.qrbnb_client.presentation.viewmodel.TagViewModel
import com.example.qrbnb_client.ui.SoftBrown
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_700
import com.example.qrbnb_client.ui.style_18_23_700
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.leftArrowIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsScreen(
    viewModel: TagViewModel= koinInject(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    var newTagText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Tags",
                navigationIcon ={
                    IconButton(onClick = {}){
                        Icon(
                            painter = painterResource(Res.drawable.leftArrowIcon),
                            contentDescription = "Back",
                            modifier=Modifier.size(24.dp)
                        )
                    }
                })
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
        ) {

            Text(
                text = "Manage item categorization",
                style= style_16_24_400(),
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
            )


            Text(
                text = "Existing Tags",
                style= style_18_23_700(),
                modifier = Modifier.padding(bottom = 16.dp),
            )

            when (uiState) {
                is TagUiState.Loading -> {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is TagUiState.Success -> {
                    val tags = (uiState as TagUiState.Success).tags

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(bottom = 24.dp),
                    ) {
                        items(tags) { tag ->
                            TagChip(tagName = tag.name)
                        }
                    }
                }

                is TagUiState.Error -> {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = (uiState as TagUiState.Error).message,
                            color = Color.Red,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "Add New Tag",
                style=style_18_23_700(),
                modifier = Modifier.padding(bottom = 16.dp),
            )


            OutlinedTextField(
                value = newTagText,
                onValueChange = { newTagText = it },
                placeholder = {
                    Text(
                        text = "Enter new tag",
                        style = style_16_24_400(),
                        color = SoftBrown
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    cursorColor = SoftBrown
                ),
                shape = RoundedCornerShape(10.dp)
            )



            Button(
                onClick = {
                    if (newTagText.isNotBlank()) {
                        viewModel.createTag(newTagText)
                        newTagText = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .defaultMinSize(minWidth = 84.dp)
                    .widthIn(max = 480.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7B7B)
                ),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp
                )
            ) {
                Text(
                    text = "Add Tag",
                    style=style_16_24_700(),
                    color = Color.White
                )
            }

        }
    }
}

@Composable
fun TagChip(tagName: String) {
    Row(
        modifier = Modifier
            .height(32.dp)
            .defaultMinSize(minWidth = 122.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF5F0F0)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = tagName,
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(16.dp))
    }
}

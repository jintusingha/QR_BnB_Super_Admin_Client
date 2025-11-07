import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrbnb_client.ui.LightPinkishGray
import com.example.qrbnb_client.ui.softRed
import com.example.qrbnb_client.ui.style_14_21_400
import com.example.qrbnb_client.ui.style_14_21_500
import com.example.qrbnb_client.ui.style_16_24_400
import com.example.qrbnb_client.ui.style_16_24_500

import org.jetbrains.compose.resources.painterResource
import qr_bnb_client.composeapp.generated.resources.Res
import qr_bnb_client.composeapp.generated.resources.categoryadd
import qr_bnb_client.composeapp.generated.resources.menuupdate
import qr_bnb_client.composeapp.generated.resources.neworder

@Composable
fun ActivityItem(
    title: String,
    timeAgo: String,
    type: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(LightPinkishGray, RoundedCornerShape(8.dp)),
            contentAlignment = androidx.compose.ui.Alignment.Center,
        ) {
            val iconRes = when (type) {
                "order" -> Res.drawable.neworder
                "menu" -> Res.drawable.menuupdate
                "category" -> Res.drawable.categoryadd
                else -> Res.drawable.categoryadd
            }

            Image(
                painter = painterResource(iconRes),
                contentDescription = type,
                modifier = Modifier.size(24.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = style_16_24_500()
            )
            Text(
                text = timeAgo,
                style = style_14_21_400(),
                color= softRed

            )
        }
    }
}

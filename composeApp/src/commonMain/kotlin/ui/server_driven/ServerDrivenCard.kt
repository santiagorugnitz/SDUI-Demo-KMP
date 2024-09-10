package ui.server_driven

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.models.server_driven.HomeCardData
import org.jetbrains.compose.resources.painterResource
import sduidashboard.composeapp.generated.resources.Res
import sduidashboard.composeapp.generated.resources.compose_multiplatform
import ui.shared.AsyncImage

@Composable
fun ServerDrivenCard(data: HomeCardData) {
    with(data) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.width(imageSize),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    modifier = Modifier.size(imageSize),
                    url = image,
                    error = {
                        Image(
                            painter = painterResource(Res.drawable.compose_multiplatform),
                            contentDescription = null
                        )
                    }
                )
                dividerColor?.let { HorizontalDivider(color = it) }
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .padding(start = 4.dp, bottom = 4.dp, end = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    title?.let { ServerDrivenText(it, Modifier) }
                    description?.let { ServerDrivenText(it, Modifier) }
                }
            }
        }

    }
}

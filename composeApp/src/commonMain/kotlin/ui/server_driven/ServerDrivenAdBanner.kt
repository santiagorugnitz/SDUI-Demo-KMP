package ui.server_driven

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import domain.models.server_driven.AdBannerData
import ui.shared.AsyncImage

@Composable
fun ServerDrivenAdBanner(data: AdBannerData) {
    with(data) {
        Surface(
            color = backgroundColor ?: MaterialTheme.colorScheme.surface
        ) {
            Box(Modifier.fillMaxWidth()) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    url = image,
                    contentScale = ContentScale.FillWidth
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterStart),
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    title?.let { ServerDrivenText(it) }
                    description?.let { ServerDrivenText(it) }
                    buttonText?.let {
                        OutlinedButton(
                            modifier = Modifier.padding(8.dp),
                            onClick = {},
                            border = BorderStroke(2.dp, it.color),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            ServerDrivenText(it, Modifier)
                        }
                    }
                }
            }
        }
    }
}
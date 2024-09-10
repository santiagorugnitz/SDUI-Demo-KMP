package com.sdui.dashboard

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.models.server_driven.HomeCardData
import domain.models.server_driven.TextData
import ui.screens.dashboard.Endpoint
import ui.screens.dashboard.editor.components.EndpointSelector
import ui.server_driven.ServerDrivenCard

@Composable
private fun Theme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview
@Composable
fun ServerDrivenCardPreview() {
    Theme {
        Row(
            Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ServerDrivenCard(
                data = HomeCardData(
                    title = TextData.Defaults.copy(
                        text = "Title",
                        textAlign = TextAlign.Center,
                        size = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    description = TextData.Defaults.copy(
                        text = "Description",
                        textAlign = TextAlign.Center,
                    ),
                    dividerColor = null,
                    image = "",
                    imageSize = 128.dp
                )
            )

            ServerDrivenCard(
                data = HomeCardData(
                    title = TextData.Defaults.copy(
                        text = "Title",
                        textAlign = TextAlign.Center,
                        size = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    description = TextData.Defaults.copy(
                        text = "Description",
                        textAlign = TextAlign.Center,
                    ),
                    dividerColor = Color.DarkGray,
                    image = "",
                    imageSize = 128.dp
                )
            )

        }
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EndpointSelectorPreview() {
    Theme {
        EndpointSelector(
            Endpoint.HOME,
            {},
            {},
            {},
            true
        )
    }
}
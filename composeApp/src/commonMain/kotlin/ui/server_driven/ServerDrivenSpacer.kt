package ui.server_driven

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.models.server_driven.SpacerData

@Composable
fun ServerDrivenSpacer(data: SpacerData) {
    Spacer(Modifier.height(data.height))
}

package ui.server_driven

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.models.server_driven.ServerDrivenData
import domain.models.server_driven.UrgentMessageData

@Composable
fun ServerDrivenUrgentMessage(data: UrgentMessageData, eventHandler: ServerDrivenData.EventHandler) {
    with(data) {
        var visible by remember(data) { mutableStateOf(true) }
        AnimatedVisibility(visible) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Column(Modifier.weight(1f)) {
                    title?.let { ServerDrivenText(it) }
                    description?.let { ServerDrivenText(it) }
                }
                if (dismissible) {
                    IconButton(
                        onClick = {
                            visible = false
                            eventHandler.onDismiss()
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            tint = title?.color ?: description?.color ?: LocalContentColor.current,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
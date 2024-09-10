package ui.screens.dashboard.editor.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import sduidashboard.composeapp.generated.resources.Res
import sduidashboard.composeapp.generated.resources.ic_save
import ui.screens.dashboard.Endpoint

@Composable
fun EndpointSelector(
    selectedEndpoint: Endpoint,
    onChange: (Endpoint) -> Unit,
    onCall: () -> Unit,
    onSave: () -> Unit,
    savingLoading: Boolean,
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            EndpointItem(
                modifier = Modifier.fillMaxWidth(),
                endpoint = selectedEndpoint,
                onClick = { expanded = true }
            )
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Endpoint.entries.forEach {
                    EndpointItem(
                        Modifier.fillMaxWidth(),
                        it
                    ) {
                        onChange(it)
                        expanded = false
                    }
                }
            }
        }
        Surface(
            modifier = Modifier
                .size(48.dp),
            color = Color.White,
            onClick = onCall
        ) {
            Icon(
                Icons.Default.PlayArrow,
                tint = Color.Black,
                contentDescription = null
            )
        }
        Surface(
            modifier = Modifier
                .size(48.dp),
            color = Color.White,
            onClick = { if (!savingLoading) onSave() }
        ) {
            if (savingLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.requiredSize(32.dp),
                )
            } else {
                Icon(
                    painterResource(Res.drawable.ic_save),
                    tint = Color.Black,
                    modifier = Modifier.requiredSize(32.dp),
                    contentDescription = null
                )
            }
        }
    }

}

@Composable
private fun EndpointItem(
    modifier: Modifier,
    endpoint: Endpoint,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier.clickable { onClick() },
        border = BorderStroke(1.dp, endpoint.color),
        color = endpoint.color.copy(alpha = 0.6f),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                color = endpoint.color
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "GET",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White,
                )
            }
            Text(
                text = endpoint.path,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
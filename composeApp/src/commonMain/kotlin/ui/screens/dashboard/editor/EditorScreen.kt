package ui.screens.dashboard.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ui.base.ContentState
import ui.screens.dashboard.DashboardViewModel
import ui.screens.dashboard.editor.components.EndpointSelector
import ui.theme.DefaultJson
import ui.theme.JsonBackground

@Composable
fun EditorScreen(
    modifier: Modifier,
    state: DashboardViewModel.State,
    onUIEvent: (DashboardViewModel.UIEvent) -> Unit
) {
    Column(modifier.background(JsonBackground)) {
        EndpointSelector(
            selectedEndpoint = state.selectedEndpoint,
            onChange = { onUIEvent(DashboardViewModel.UIEvent.EndpointSelect(it)) },
            onCall = { onUIEvent(DashboardViewModel.UIEvent.EndpointCall) },
            onSave = { onUIEvent(DashboardViewModel.UIEvent.EndpointSave) },
            savingLoading = state.savingLoading
        )
        ContentState(
            modifier = Modifier.weight(1f),
            loadState = state.loadState
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    value = state.json,
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = JsonBackground,
                        unfocusedContainerColor = JsonBackground,
                        focusedTextColor = DefaultJson,
                        unfocusedTextColor = DefaultJson
                    ),
                    onValueChange = { onUIEvent(DashboardViewModel.UIEvent.JsonChange(it)) },
                    visualTransformation = JsonVisualTransformation(),
                )
                if (state.jsonError.isNotEmpty()) {
                    Surface(
                        color = Color.Red
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = state.jsonError,
                            color = Color.White,
                            maxLines = 2,
                        )
                    }
                }
            }
        }
    }
}

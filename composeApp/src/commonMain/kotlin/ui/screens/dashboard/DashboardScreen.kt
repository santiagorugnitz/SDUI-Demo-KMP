package ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.stringResource
import sduidashboard.composeapp.generated.resources.Res
import sduidashboard.composeapp.generated.resources.compact_mode_button
import ui.screens.dashboard.editor.EditorScreen
import ui.screens.dashboard.preview.CompactPreviewScreen
import ui.screens.dashboard.preview.PreviewScreen
import ui.theme.DividerColor

@Composable
internal fun DashboardScreen() {
    val viewModel: DashboardViewModel = viewModel { DashboardViewModel() }

    val state by viewModel.stateFlow.collectAsState()

    Content(state, viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun Content(
    state: DashboardViewModel.State,
    onUIEvent: (DashboardViewModel.UIEvent) -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass()
    Scaffold {
        Box {
            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {

                BoxWithConstraints {
                    var weight by remember { mutableFloatStateOf(0.5f) }
                    val width = with(LocalDensity.current) { maxWidth.toPx() }

                    Row {
                        EditorScreen(
                            modifier = Modifier.weight(weight),
                            state = state,
                            onUIEvent = onUIEvent
                        )
                        VerticalDivider(
                            modifier = Modifier.draggable(
                                orientation = Orientation.Horizontal,
                                state = rememberDraggableState { delta ->
                                    weight = (weight + delta / width).coerceIn(0.1f, 0.9f)
                                }
                            ),
                            color = DividerColor,
                            thickness = 8.dp
                        )
                        PreviewScreen(
                            modifier = Modifier.weight(1 - weight),
                            state = state,
                        )
                    }
                }
            } else {
                var showPreview by remember { mutableStateOf(true) }
                Column(Modifier.fillMaxSize()) {
                    Text(
                        stringResource(Res.string.compact_mode_button),
                        color = Color.LightGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.DarkGray)
                            .clickable { showPreview = !showPreview },
                        textAlign = TextAlign.Center
                    )
                    if (showPreview) {
                        CompactPreviewScreen(
                            modifier = Modifier.weight(1f).fillMaxWidth(),
                            state = state
                        )
                    } else {
                        EditorScreen(
                            modifier = Modifier.weight(1f).fillMaxWidth(),
                            state = state,
                            onUIEvent = onUIEvent
                        )
                    }
                }
            }
        }
    }
}

enum class Endpoint(val path: String, val color: Color) {
    HOME("/screens/home", Color(0xFF91C66E)),
    MESSAGES("/screens/messages", Color(0xFF49AFF2))
}

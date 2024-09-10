package ui.screens.dashboard.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import sduidashboard.composeapp.generated.resources.Res
import sduidashboard.composeapp.generated.resources.dark_mode
import sduidashboard.composeapp.generated.resources.light_mode
import ui.base.ContentState
import ui.base.LoadState
import ui.screens.dashboard.DashboardViewModel
import ui.server_driven.ServerDrivenComposable

@Composable
fun CompactPreviewScreen(
    modifier: Modifier,
    state: DashboardViewModel.State
) {
    var darkMode by remember { mutableStateOf(false) }
    ContentState(
        modifier = modifier,
        loadState = LoadState.ShowContent
    ) {
        MaterialTheme(
            colorScheme = if (darkMode) darkColorScheme() else lightColorScheme()
        ) {
            Surface {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Column(Modifier.fillMaxSize().padding(4.dp)) {
                        Column {
                            state.components?.header?.forEach {
                                ServerDrivenComposable(it)
                            }
                        }
                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(state.components?.content.orEmpty()) {
                                ServerDrivenComposable(it)
                            }
                        }
                        Column {
                            state.components?.footer?.forEach {
                                ServerDrivenComposable(it)
                            }
                        }
                    }
                    IconButton(
                        modifier = Modifier
                            .padding(16.dp),
                        onClick = { darkMode = !darkMode },
                        colors = IconButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black,
                            disabledContentColor = Color.Black,
                            disabledContainerColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(if (darkMode) Res.drawable.dark_mode else Res.drawable.light_mode),
                            modifier = Modifier.padding(8.dp),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

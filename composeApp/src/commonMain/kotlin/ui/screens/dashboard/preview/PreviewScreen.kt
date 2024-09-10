package ui.screens.dashboard.preview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import sduidashboard.composeapp.generated.resources.system_bar
import ui.base.ContentState
import ui.base.LoadState
import ui.screens.dashboard.DashboardViewModel
import ui.server_driven.ServerDrivenComposable
import ui.theme.PreviewBackground

@Composable
fun PreviewScreen(
    modifier: Modifier,
    state: DashboardViewModel.State
) {
    var darkMode by remember { mutableStateOf(false) }
    ContentState(
        modifier = modifier,
        loadState = LoadState.ShowContent
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(PreviewBackground),
            contentAlignment = Alignment.Center
        ) {
            MaterialTheme(
                colorScheme = if (darkMode) darkColorScheme() else lightColorScheme()
            ) {
                Surface(
                    modifier = Modifier
                        .aspectRatio(360f / 780f)
                        .fillMaxSize()
                        .padding(4.dp),
                    border = BorderStroke(4.dp, Color.Black),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(Modifier.fillMaxSize().padding(4.dp)) {
                        Image(
                            painter = painterResource(Res.drawable.system_bar),
                            modifier = Modifier.fillMaxWidth(),
                            contentDescription = null
                        )
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
                }
            }
            IconButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
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

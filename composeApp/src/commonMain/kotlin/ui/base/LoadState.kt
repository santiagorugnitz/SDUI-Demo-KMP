package ui.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import sduidashboard.composeapp.generated.resources.Res
import sduidashboard.composeapp.generated.resources.retry


@Composable
fun ContentState(
    modifier: Modifier = Modifier.fillMaxSize(),
    loadState: LoadState,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (loadState) {
            is LoadState.Empty -> {}
            is LoadState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(loadState.message)
                    loadState.callback?.let {
                        Button(onClick = it) {
                            Text(stringResource(Res.string.retry))
                        }
                    }
                }
            }

            is LoadState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            is LoadState.ShowContent -> {
                content()
            }
        }
    }
}

sealed class LoadState {
    data object Empty : LoadState()
    data object Loading : LoadState()
    data object ShowContent : LoadState()
    data class Error(val message: String, val callback: (() -> Unit)?) : LoadState()
}
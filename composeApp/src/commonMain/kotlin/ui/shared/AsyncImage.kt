package ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageScope


@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillHeight,
    url: String,
    error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = null
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = null,
        contentScale = contentScale,
        error = error
    )
}
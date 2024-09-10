package ui.server_driven

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import domain.models.server_driven.TextData

@Composable
fun ServerDrivenText(
    data: TextData,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    with(data) {
        Text(
            modifier = modifier,
            text = text,
            textAlign = textAlign,
            color = color,
            fontWeight = fontWeight,
            fontSize = size,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}
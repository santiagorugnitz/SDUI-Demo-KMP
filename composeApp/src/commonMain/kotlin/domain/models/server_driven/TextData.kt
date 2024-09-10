package domain.models.server_driven

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TextData(
    val text: String,
    val textAlign: TextAlign,
    val color: Color,
    val size: TextUnit,
    val fontWeight: FontWeight,
    val maxLines: Int,
) : ServerDrivenData() {

    companion object {
        val Defaults = TextData(
            "",
            TextAlign.Start,
            Color.Unspecified,
            16.sp,
            FontWeight.Normal,
            Int.MAX_VALUE
        )
    }
}
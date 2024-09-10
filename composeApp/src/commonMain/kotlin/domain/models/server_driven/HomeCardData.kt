package domain.models.server_driven

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class HomeCardData(
    val title: TextData?,
    val description: TextData?,
    val dividerColor: Color?,
    val image: String,
    val imageSize: Dp,
) : ServerDrivenData()

data class HomePagerData(
    val items: List<HomeCardData>
) : ServerDrivenData()
package domain.models.server_driven

import androidx.compose.ui.graphics.Color

data class AdBannerData(
    val title: TextData?,
    val description: TextData?,
    val backgroundColor: Color?,
    val buttonText: TextData?,
    val image: String,
) : ServerDrivenData()
package domain.models.server_driven

import androidx.compose.ui.graphics.Color

data class UrgentMessageData(
    val title: TextData?,
    val description: TextData?,
    val backgroundColor: Color,
    val dismissible: Boolean,
) : ServerDrivenData()
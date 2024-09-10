package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UrgentMessageResponse(
    @SerialName("title") val title: TextResponse? = null,
    @SerialName("description") val description: TextResponse? = null,
    @SerialName("background_color") val backgroundColor: String?,
    @SerialName("dismissible") val dismissible: Boolean,
) : ServerDrivenComponentResponse()
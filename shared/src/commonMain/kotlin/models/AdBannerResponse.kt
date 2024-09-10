package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdBannerResponse(
    @SerialName("title") val title: TextResponse?,
    @SerialName("description") val description: TextResponse?,
    @SerialName("background_color") val backgroundColor: String? = null,
    @SerialName("button_text") val buttonText: TextResponse?,
    @SerialName("image") val image: String,
) : ServerDrivenComponentResponse()
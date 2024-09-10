package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomePagerResponse(
    @SerialName("items") val items: List<HomeCardResponse>,
) : ServerDrivenComponentResponse()

@Serializable
data class HomeCardResponse(
    @SerialName("title") val title: TextResponse? = null,
    @SerialName("description") val description: TextResponse? = null,
    @SerialName("divider_color") val dividerColor: String? = null,
    @SerialName("image") val image: String,
    @SerialName("image_size") val imageSize: Int? = null,
) : ServerDrivenComponentResponse()
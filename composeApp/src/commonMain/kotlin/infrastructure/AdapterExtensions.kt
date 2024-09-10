package infrastructure

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.models.server_driven.AdBannerData
import domain.models.server_driven.HomeCardData
import domain.models.server_driven.HomePagerData
import domain.models.server_driven.ServerDrivenData
import domain.models.server_driven.ServerDrivenScreen
import domain.models.server_driven.SpacerData
import domain.models.server_driven.TextData
import domain.models.server_driven.UrgentMessageData
import models.AdBannerResponse
import models.HomeCardResponse
import models.HomePagerResponse
import models.ServerDrivenComponentResponse
import models.ServerDrivenScreenResponse
import models.SpacerResponse
import models.TextResponse
import models.UrgentMessageResponse

fun SpacerResponse.toDomainModelClass() = SpacerData(
    height = height.dp
)

fun TextResponse.toDomainModelClass(default: TextData = TextData.Defaults) = TextData(
    text = text,
    textAlign = when (textAlign) {
        "CENTER" -> TextAlign.Center
        "END" -> TextAlign.End
        else -> default.textAlign
    },
    color = color?.let { parseColor(it) } ?: default.color,
    size = fontSize?.sp ?: default.size,
    fontWeight = when (fontWeight) {
        "BOLD" -> FontWeight.Bold
        "SEMI_BOLD" -> FontWeight.SemiBold
        "THIN" -> FontWeight.Thin
        else -> default.fontWeight
    },
    maxLines = maxLines ?: default.maxLines
)

fun AdBannerResponse.toDomainModelClass() = AdBannerData(
    title?.toDomainModelClass(TextData.Defaults.copy(fontWeight = FontWeight.SemiBold, size = 18.sp)),
    description?.toDomainModelClass(),
    backgroundColor?.let { parseColor(it) },
    buttonText?.toDomainModelClass(),
    image
)

fun HomeCardResponse.toDomainModelClass() = HomeCardData(
    title?.toDomainModelClass(
        TextData.Defaults.copy(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            size = 12.sp,
            maxLines = 1
        )
    ),
    description?.toDomainModelClass(
        TextData.Defaults.copy(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            size = 12.sp,
            maxLines = 1
        )
    ),
    dividerColor?.let { parseColor(it) },
    image,
    (imageSize ?: 128).dp
)

fun HomePagerResponse.toDomainModelClass() = HomePagerData(
    items.map { it.toDomainModelClass() }
)

fun UrgentMessageResponse.toDomainModelClass() = UrgentMessageData(
    title = title?.toDomainModelClass(TextData.Defaults.copy(fontWeight = FontWeight.SemiBold, size = 18.sp)),
    description = description?.toDomainModelClass(),
    backgroundColor = backgroundColor?.let { parseColor(it) } ?: Color.Unspecified,
    dismissible = dismissible
)


fun ServerDrivenComponentResponse?.toDomainModelClass(): ServerDrivenData? {
    return when (this) {
        is TextResponse -> toDomainModelClass()
        is SpacerResponse -> toDomainModelClass()
        is AdBannerResponse -> toDomainModelClass()
        is HomeCardResponse -> toDomainModelClass()
        is HomePagerResponse -> toDomainModelClass()
        is UrgentMessageResponse -> toDomainModelClass()
        null -> null
    }
}

fun ServerDrivenScreenResponse.toDomainModelClass(): ServerDrivenScreen =
    ServerDrivenScreen(
        header.mapNotNull { it.toDomainModelClass() },
        content.mapNotNull { it.toDomainModelClass() },
        footer.mapNotNull { it.toDomainModelClass() },
    )

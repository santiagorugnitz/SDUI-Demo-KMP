package ui.server_driven

import androidx.compose.runtime.Composable
import domain.models.server_driven.AdBannerData
import domain.models.server_driven.HomeCardData
import domain.models.server_driven.HomePagerData
import domain.models.server_driven.ServerDrivenData
import domain.models.server_driven.SpacerData
import domain.models.server_driven.TextData
import domain.models.server_driven.UrgentMessageData

@Composable
fun ServerDrivenComposable(
    data: ServerDrivenData,
    eventHandler: ServerDrivenData.EventHandler = object : ServerDrivenData.EventHandler {}
) {
    when (data) {
        is SpacerData -> ServerDrivenSpacer(data)
        is TextData -> ServerDrivenText(data)
        is AdBannerData -> ServerDrivenAdBanner(data)
        is HomeCardData -> ServerDrivenCard(data)
        is HomePagerData -> ServerDrivenPager(data)
        is UrgentMessageData -> ServerDrivenUrgentMessage(data, eventHandler)
    }
}
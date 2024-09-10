package domain.repositories

import ui.screens.dashboard.Endpoint

object MockRepository : IRepository {

    private val cache: MutableMap<Endpoint, String> = mutableMapOf(
        Pair(
            Endpoint.HOME,
            "{\"header\": [ {\"__name__\": \"models.TextResponse\",\"text\": \"Un nombre re lindo para la app\",\"alignment\": \"CENTER\",\"size\": 13,\"weight\": \"BOLD\" }],\"content\": [ {\"__name__\": \"models.SpacerResponse\",\"height\": 32},{\"__name__\": \"models.TextResponse\",\"text\": \"  Lo que más se pide\",\"size\": 24,\"weight\": \"BOLD\"},{\"__name__\": \"models.SpacerResponse\",\"height\": 8},{\"__name__\": \"models.HomePagerResponse\",\"items\": [{\"title\": {    \"text\": \"Hamburguesas\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/categories%2Fburger.jpg?alt=media\"},{\"title\": {    \"text\": \"Mexicana\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/categories%2Fmexican.jpg?alt=media\"},{\"title\": {    \"text\": \"Pizzas\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/categories%2Fpizza.png?alt=media\"},{\"title\": {    \"text\": \"Helados\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/categories%2Fice-cream.jpg?alt=media\"},{\"title\": {    \"text\": \"Asiática\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/categories%2Fasian.jpg?alt=media\"},{\"title\": {    \"text\": \"Café\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/categories%2Fcoffee.jpg?alt=media&token=a4ea0796-065e-4738-8a29-dc004856270f\"}]},{\"__name__\": \"models.SpacerResponse\",\"height\": 32},{\"__name__\": \"models.AdBannerResponse\",\"title\": {\"text\": \"Día de la hamburguesa\",\"color\": \"#000B90\",\"size\": 24,\"weight\": \"BOLD\"},\"description\": {\"text\": \"Aprovecha los descuentos \\nque tenemos para ti\",\"color\": \"#000B90\",\"size\": 14,\"weight\": \"NORMAL\"},\"button_text\": {\"text\": \"PEDIR\",\"color\": \"#000B90\",\"size\": 14,\"weight\": \"BOLD\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/ads%2Fburger-day.jpg?alt=media\"},{\"__name__\": \"models.SpacerResponse\",\"height\": 32},{\"__name__\": \"models.TextResponse\",\"text\": \"  Tus favoritos\",\"size\": 24,\"weight\": \"BOLD\"},{\"__name__\": \"models.SpacerResponse\",\"height\": 8},{ \"__name__\": \"models.HomePagerResponse\",\"items\": [{ \"title\": {\"text\": \"Envío\" },\"description\": {\"text\": \"\$60\" },\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/restaurants%2Fmexican.jpg?alt=media\"},{ \"title\": {\"text\": \"Envío\" },\"description\": { \"text\": \"\$45\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/restaurants%2Fvegan.jpg?alt=media\"},{ \"title\": {\"text\": \"Envío\" },\"description\": { \"text\": \"\$25\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/restaurants%2Frandom.jpg?alt=media\"}]},{ \"__name__\": \"models.SpacerResponse\",\"height\": 32 },{ \"__name__\": \"models.AdBannerResponse\",\"title\": {\"text\": \"Viandas semanales\",\"color\": \"#F5F5F5\",\"size\": 24,\"weight\": \"BOLD\" },\"description\": {\"text\": \"Tenemos suscripciones \\nque se adaptan a \\ntus necesidades\",\"color\": \"#F5F5F5\",\"size\": 12,\"weight\": \"NORMAL\"},\"button_text\": {\"text\": \"DESCURIR\",\"color\": \"#F5F5F5\",\"size\": 14,\"weight\": \"BOLD\"},\"image\": \"https://firebasestorage.googleapis.com/v0/b/sdui-dashboard.appspot.com/o/ads%2Flunch-left.jpeg?alt=media\"},{ \"__name__\": \"models.SpacerResponse\",\"height\": 16 }],\"footer\": []}"
        ),
        Pair(
            Endpoint.MESSAGES,
            "{\"header\":[],\"content\":[{\"__name__\": \"models.UrgentMessageResponse\",\"title\": {\"text\": \"Error\",\"color\": \"#FFFFFF\"},\"description\": {\"text\": \"desc\",\"color\": \"#FFFFFF\"},\"background_color\": \"#FF0000\",\"dismissible\": true},{\"__name__\": \"models.UrgentMessageResponse\",\"title\": {\"text\": \"Error\",\"color\": \"#FFFFFF\"},\"description\": {\"text\": \"desc\",\"color\": \"#FFFFFF\"},\"background_color\": \"#00FF00\",\"dismissible\": true},{\"__name__\": \"models.UrgentMessageResponse\",\"title\": {\"text\": \"Error\",\"color\": \"#FFFFFF\"},\"description\": {\"text\": \"desc\",\"color\": \"#FFFFFF\"},\"background_color\": \"#FEFEFE\",\"dismissible\": true}],\"footer\":[]}"
        )

    )

    override suspend fun getRawResponse(endpoint: Endpoint): ActionResult<String> {
        return ResponseHandler {
            cache[endpoint].orEmpty()
        }
    }

    override suspend fun updateResponse(endpoint: Endpoint, json: String): ActionResult<Unit> {
        return ResponseHandler {
            cache[endpoint] = json
        }
    }

    override fun getFromCache(endpoint: Endpoint): String {
        return cache[endpoint].orEmpty()
    }

}


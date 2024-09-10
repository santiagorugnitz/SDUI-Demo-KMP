import kotlinx.serialization.json.Json

val jsonFormatter = Json {
    classDiscriminator = CLASS_DISCRIMINATOR
    prettyPrint = true
}
package com.valu.valulibrary.utils

import com.valu.valulibrary.model.Category
import com.valu.valulibrary.model.Product
import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.serializer

val jsonWorker = Json {
    ignoreUnknownKeys = true // ¡CRUCIAL! Ignora campos que no estén en tu data class
    isLenient = true         // Permite formatos de JSON más flexibles (comas de más, etc.)
    coerceInputValues = true // Si el JSON manda algo raro, usa el valor por defecto de tu clase
    encodeDefaults = true    // Asegura que los valores por defecto se usen
}
fun String.parseListProduct(): List<Product> {
    return try {
        jsonWorker.decodeFromString<List<Product>>(this.trim())
    } catch (e: Exception) {
        println("Error detallado parseListProduct: ${e.message}")
        emptyList()
    }
}

fun String.parseListCategory(): List<Category> {
    return try {
        jsonWorker.decodeFromString<List<Category>>(this.trim())
    } catch (e: Exception) {
        println("Error detallado parseListCategory: ${e.message}")
        emptyList()
    }
}



internal inline fun <reified R : Any> String.parseJsonTo() =
    decodeFromString<R>(this)

object InstantSerializer: KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Instant {
        val jsonPrimitive = (decoder as JsonDecoder).decodeJsonElement().jsonPrimitive
        return Instant.parse(jsonPrimitive.content)
    }

    override fun serialize(encoder: Encoder, value: Instant) {
        return encoder.encodeString(value.toString())
    }
}

inline fun <reified T : Any> T.toJsonString(
): String {
    val appJson = Json {
        prettyPrint = true       // Para JSON legible
        ignoreUnknownKeys = true // Útil para la deserialización (aunque aquí solo serializamos)
        encodeDefaults = true    // Incluye propiedades con valores predeterminados
    }

    return appJson.encodeToString(serializer<T>(), this)
}
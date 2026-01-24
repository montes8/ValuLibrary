package com.valu.valulibrary.di


import com.valu.valulibrary.repository.network.exception.ErrorAuthorization
import com.valu.valulibrary.repository.network.exception.ErrorGeneric
import com.valu.valulibrary.repository.network.exception.ExceptionMapperSoap
import com.valu.valulibrary.requestLogger
import com.valu.valulibrary.utils.InstantSerializer
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        useAlternativeNames = false
                        ignoreUnknownKeys = true // ¡CRUCIAL! Ignora campos que no estén en tu data class
                        isLenient = true         // Permite formatos de JSON más flexibles (comas de más, etc.)
                        coerceInputValues = true // Si el JSON manda algo raro, usa el valor por defecto de tu clase
                        encodeDefaults = true
                        serializersModule = SerializersModule {
                            contextual(Instant::class, InstantSerializer)
                        }
                    },
                )
            }

            HttpResponseValidator {
                validateResponse { response ->
                    if (response.status != HttpStatusCode.OK) {
                        val statusCode = response.status.value
                        val error =  response.bodyAsText()
                        when (statusCode) {
                            in 300..399 -> throw ErrorGeneric()
                            in 400..499 ->{
                                if (statusCode == 401){
                                    throw ErrorAuthorization()
                                }else{
                                    println("ResponsebodyError: ${
                                        ExceptionMapperSoap(
                                            response.status.value,
                                            error
                                        )
                                    }")
                                    throw ExceptionMapperSoap(response.status.value,error)

                                   // throw ExceptionMapper(response.status.value, error.parseJsonTo<ApiException>())
                                }
                            }
                            in 500..599 -> {
                                println("ResponsebodyError: ${ExceptionMapperSoap(response.status.value,error)}")
                                throw ExceptionMapperSoap(response.status.value,error)
                               // throw ExceptionMapper(response.status.value, error.parseJsonTo<ApiException>())
                            }
                        }
                    }
                }
            }

            install(Logging) {
                logger = requestLogger
                level = LogLevel.ALL
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 60_000
                requestTimeoutMillis = 60_000
            }
        }
    }
}
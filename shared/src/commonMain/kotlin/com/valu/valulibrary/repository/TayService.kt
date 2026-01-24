package com.valu.valulibrary.repository

import com.valu.valulibrary.model.Product
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path

class TayService(private val client: HttpClient) {

    inline fun <reified T>HttpRequestBuilder.pathUrlPost(path: String, body : T){
        url {
            protocol = URLProtocol.HTTPS
            host = "servertay.onrender.com"
            path(path)
            // parameters.append("token", "abc123")
            //headers { append(HttpHeaders.Authorization, "abc123")}
            contentType(ContentType.Application.Json)//tipo de dato
            setBody(body) //metodos post body
        }
    }

    fun HttpRequestBuilder.pathUrlGet(urlSecond: String){
        url {
            protocol = URLProtocol.HTTPS
            host = "servertay.onrender.com"
            path(urlSecond)
            // parameters.append("token", "abc123")
            //headers { append(HttpHeaders.Authorization, "abc123")}
            contentType(ContentType.Application.Json)
        }
    }
    suspend fun getLocation(): String{
        val response = client.get {
            pathUrlGet("services/product")
        }
        return response.bodyAsText()
    }

    suspend fun postLocation(body : Product): String{
        val response = client.post {
            pathUrlPost("services/product",body)
        }
        return response.bodyAsText()
    }

}
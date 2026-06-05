package com.valu.valulibrary.repository.network

import com.valu.valulibrary.model.Product
import com.valu.valulibrary.utils.getUrlAppTay
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

    inline fun <reified T> HttpRequestBuilder.pathUrlPost(path: String, body : T){
        url {
            protocol = URLProtocol.Companion.HTTPS
            host = getUrlAppTay()
            path(path)
            // parameters.append("token", "abc123")
            //headers { append(HttpHeaders.Authorization, "abc123")}
            contentType(ContentType.Application.Json)//tipo de dato
            setBody(body) //metodos post body
        }
    }

    fun HttpRequestBuilder.pathUrlGet(urlSecond: String){
        url {
            protocol = URLProtocol.Companion.HTTPS
            host = getUrlAppTay()
            path(urlSecond)
            // parameters.append("token", "abc123")
            //headers { append(HttpHeaders.Authorization, "abc123")}
            contentType(ContentType.Application.Json)
        }
    }
    suspend fun loadData(): String{
        val response = client.get {
            pathUrlGet("services/product")
        }
        return response.bodyAsText()
    }

    suspend fun loadCategory(): String{
        val response = client.get {
            pathUrlGet("services/category")
        }
        return response.bodyAsText()
    }

    suspend fun loadParam(): String{
        val response = client.get {
            pathUrlGet("services/param")
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
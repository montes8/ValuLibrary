package com.valu.valulibrary.repository.network.exception

import kotlinx.serialization.Serializable

data class ExceptionMapper(
    val code : Int = 0,
    val apiException : ApiException = ApiException()
): Exception()

data class ExceptionMapperSoap(
    val code : Int = 0,
    val apiExceptionSoap : String = ""
): Exception()

@Serializable
data class ApiException(
    val errorCode : Int = 0,
    val errorMessage : String = "Error generic ",
    val errorMessageDetail : String = "Error generic detail",
)


class ErrorAuthorization: Exception()

class ErrorNetwork: Exception()

class ErrorGeneric: Exception()
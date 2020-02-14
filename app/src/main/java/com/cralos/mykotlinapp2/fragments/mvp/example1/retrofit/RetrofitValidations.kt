package com.cralos.mykotlinapp2.fragments.mvp.example1.retrofit

import java.io.IOException

class RetrofitValidations {

    companion object {

        fun checkSuccessCode(code: Int): Boolean {
            return code == 200
        }

        fun getErrorByCode(code: Int): String {
            return when (code) {
                400 -> return "Error, la solicitud contiene sintaxis errónea"
                401 -> return "Error, solicitud no autorizada"
                404 -> return "Error, recurso no encontrado"
                500 -> return "Error, se ha producido un error en la conexión con el servidor, intente más tarde"
                else -> "error desconocido"
            }
        }

        fun getOnFailtureResponse(t: Throwable): String {
            if (t is IOException) {
                return "Error, verifica tu conexión a internet"
            } else if (t is IllegalStateException) {
                return "Error, la respuesta contiene un formato que no se puede leer"
            } else {
                return "Error desconocido"
            }
        }
    }

}
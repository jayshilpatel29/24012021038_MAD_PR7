package com.jayshil.a24012021038_mad_pr7

import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class HttpRequest {

    fun makeServiceCall(
        reqUrl:String?,
        token:String?=null
    ):String? {

        var response:String? = null

        try {

            val url = URL(reqUrl)

            val conn =
                url.openConnection() as HttpURLConnection

            if(token != null){

                conn.setRequestProperty(
                    "Authorization",
                    "Bearer $token"
                )

                conn.setRequestProperty(
                    "Content-Type",
                    "application/json"
                )
            }

            conn.requestMethod = "GET"

            response =
                BufferedInputStream(
                    conn.inputStream
                ).bufferedReader().use {
                    it.readText()
                }

        }
        catch (e:Exception){
            e.printStackTrace()
        }

        return response
    }
}
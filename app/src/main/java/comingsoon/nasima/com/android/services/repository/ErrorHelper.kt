package com.cognitiveclouds.ds.services.repository

import okhttp3.ResponseBody
import org.json.JSONObject

fun  getErrorMessage(responseBody: ResponseBody?) : String {

    return try {
        val jsonObject = JSONObject(responseBody?.string())
        val error = JSONObject(jsonObject.getString("error"))
        error["message"].toString()
    } catch (e : Exception) {
        e.message.toString()
    }
}

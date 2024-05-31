package com.eskaya.task_sanstech.utils.extensions


import org.json.JSONObject
import retrofit2.HttpException

fun HttpException.handleError(): String {

    var errorMessage = ""
    errorMessage = try {
        val jObjError = JSONObject(this.response()?.errorBody()?.string())
        jObjError.getJSONObject("status_message").getString("status_message")

    } catch (e: Exception) {
        e.message.toString()
    }
    return errorMessage
}

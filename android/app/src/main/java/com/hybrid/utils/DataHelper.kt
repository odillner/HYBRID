package com.hybrid.utils

import android.os.Build
import android.os.StrictMode
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.ceil

class DataHelper {
    fun encode(data: Array<String>) : Array<ByteArray> {
        return Array(data.size){data[it].toByteArray()}
    }

    fun decode(data: Array<ByteArray>) : Array<String> {
        return Array(data.size){String(data[it])}
    }

    fun chunk(data: ByteArray, size: Int) : Array<ByteArray> {
        val len = ceil(data.size.toDouble()/size).toInt()

        return Array(len){data.copyOfRange(it * size,  if ((it+1) * size < data.size) (it+1) * size else data.size)}
    }

    fun dechunk(data: Array<ByteArray>) : ByteArray {
        var dechunked = byteArrayOf()

        for (element in data) {
            dechunked += element
        }

        return dechunked
    }

    fun inputToDataSet(input: String) : Array<String> {
        val split = input.split("}}},")

        return Array(split.size){split[it] + "}}}"}
    }


    fun uploadResult(result: RunResult, algorithmName: String, dataSetSize: Int, apiURL: String) {
        val body = JSONObject()

        body.put("platform", "NATIVE")
        body.put("deviceBrand", Build.BRAND.uppercase())
        body.put("deviceModel", Build.MODEL.uppercase())
        body.put("deviceOS", "ANDROID" + Build.VERSION.RELEASE)
        body.put("algorithm", algorithmName)
        body.put("dataSetSize", dataSetSize)
        body.put("encryptTimings", JSONArray(result.encryptTimings))
        body.put("decryptTimings", JSONArray(result.decryptTimings))

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().build())

        Fuel.post(apiURL).jsonBody(body.toString()).response()
    }
}
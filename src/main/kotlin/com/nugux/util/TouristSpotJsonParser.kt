package com.nugux.util

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.nugux.model.TouristSpot
import kotlin.random.Random

class TouristSpotJsonParser {
    private val jsonParser: JsonParser = JsonParser()
    private lateinit var jsonObject: JsonObject

    companion object {
        const val KEY_RESULTS = "results"
        const val KEY_BINDING = "bindings"
        const val KEY_VALUE = "value"
    }

    /*
        원래는 혼잡도를 TMap API를 통해 daily로 schedule job을 이용해 저장해야 되나 API 사용량 한계(무료 계정)으로 인하여
        random 값으로 daily가 아닌 관광지 정보에 static하게 저장하여 계산에 이용
     */
    fun makeObjects(jsonFilePath: String): List<TouristSpot> {
        jsonObject = getJsonObject(getJsonText(jsonFilePath))
        return getJsonArray().map { jsonElement ->
            val jsonObject = jsonElement as JsonObject
            val name = getStringValue(jsonObject, "name")
            val address = getStringValue(jsonObject, "address")
            val postalCode = getStringValue(jsonObject, "postalCode")
            val description = getStringValue(jsonObject, "description")
            val lat = getStringValue(jsonObject, "lat1").toDouble()
            val long = getStringValue(jsonObject, "long1").toDouble()
            val congestion = Random.nextDouble(1.0, 3.5)

            TouristSpot(
                name = name,
                address = address,
                postalCode = postalCode,
                description = description,
                lat = lat,
                long = long,
                congestion = congestion,
                premium = (congestion < 1.5 || congestion > 3.0)
            )
        }.toList()
    }

    private fun getJsonText(jsonFilePath: String): String = this::class.java.getResource(jsonFilePath).readText()

    private fun getJsonObject(jsonText: String) = jsonParser.parse(jsonText) as JsonObject

    private fun getJsonArray() = jsonObject.getAsJsonObject(KEY_RESULTS).get(KEY_BINDING) as JsonArray

    private fun getStringValue(jsonObject: JsonObject, key: String) =
        jsonObject.getAsJsonObject(key).get(KEY_VALUE).asString
}
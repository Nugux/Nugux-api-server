package com.nugux.util

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.nugux.model.TouristSpot

class TouristSpotJsonParser {
    private val jsonParser: JsonParser = JsonParser()
    private lateinit var jsonObject: JsonObject

    companion object {
        const val KEY_RESULTS = "results"
        const val KEY_BINDING = "bindings"
        const val KEY_VALUE = "value"
    }

    fun makeObjects(jsonFilePath: String): List<TouristSpot> {
        jsonObject = getJsonObject(getJsonText(jsonFilePath))
        return getJsonArray().map { jsonElement ->
            val jsonObject = jsonElement as JsonObject
            val name = getStringValue(jsonObject, "name")
            val address = getStringValue(jsonObject, "address")
            val postalCode = getStringValue(jsonObject, "postalCode")
            val description = getStringValue(jsonObject, "description")
            val lat = getStringValue(jsonObject, "lat1")
            val long = getStringValue(jsonObject, "long1")

            TouristSpot(
                name = name,
                address = address,
                postalCode = postalCode,
                description = description,
                lat = lat.toFloat(),
                long = long.toFloat()
            )
        }.toList()
    }

    private fun getJsonText(jsonFilePath: String): String = this::class.java.getResource(jsonFilePath).readText()

    private fun getJsonObject(jsonText: String) = jsonParser.parse(jsonText) as JsonObject

    private fun getJsonArray() = jsonObject.getAsJsonObject(KEY_RESULTS).get(KEY_BINDING) as JsonArray

    private fun getStringValue(jsonObject: JsonObject, key: String) =
        jsonObject.getAsJsonObject(key).get(KEY_VALUE).asString
}

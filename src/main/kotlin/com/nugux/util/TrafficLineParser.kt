package com.nugux.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.nugux.model.LatLng
import com.nugux.model.TrafficLine

class TrafficLineParser {
    private lateinit var jsonObject: JsonObject
    private val jsonParser: JsonParser = JsonParser()

    companion object {
        const val KEY_FEATURES = "features"
    }

    fun makeObjects(jsonText: String, target: LatLng): List<TrafficLine> {
        jsonObject = getJsonObject(jsonText)
        val t:List<TrafficLine?> = getJsonArray().map { jsonElement ->
            val currentObject = jsonElement as JsonObject
            val geometry = currentObject.getAsJsonObject("geometry")
            if(geometry.get("type").asString != "LineString") {
                null
            } else {
                val translated = geometry.getAsJsonArray("coordinates").map {
                    val arr = it.asJsonArray
                    LatLng(
                        lng = arr[0].asDouble,
                        lat = arr[1].asDouble
                    )
                }
                val properties = currentObject.getAsJsonObject("properties")
                // TODO : 도로 중간 좌표의 기준??
                val center = translated[translated.size / 2]
                TrafficLine(
                    roadId = properties.get("index").asInt,
                    congestion = properties.get("congestion").asInt,
                    speed = properties.get("speed").asDouble,
                    center = center,
                    target = target
                )
            }
        }.toList()
        return t.filterNotNull()
    }
    private fun getJsonObject(jsonText: String) = jsonParser.parse(jsonText) as JsonObject

    private fun getJsonArray() = jsonObject.getAsJsonArray(KEY_FEATURES)
}
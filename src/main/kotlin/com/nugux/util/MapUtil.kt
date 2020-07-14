package com.nugux.util

import com.nugux.model.LatLng

fun getTrafficInformation (latlng:LatLng, zoomLevel:Int, projectKey:String? = null) :String{
    val (lat,lng) = latlng
    val type = "AUTO"
    val version = 1
    val key = projectKey ?: getProjectKey()

    val basicUrl = "https://apis.openapi.sk.com/tmap/traffic?"
    val requestUrl = "${basicUrl}version=${version}&trafficType=${type}&centerLat=${lat}&centerLon=${lng}&zoomLevel=${zoomLevel}&appKey=${key}"

    return java.net.URL(requestUrl).readText()
}
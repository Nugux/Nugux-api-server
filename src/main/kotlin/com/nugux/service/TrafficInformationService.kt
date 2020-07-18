package com.nugux.service

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.model.LatLng
import com.nugux.repository.DailySpotCongestionRepository
import org.springframework.stereotype.Service

@Service
class TrafficInformationService(private val projectKey: String) :
    ILogging by LoggingImpl<TrafficInformationService>() {

    fun getTrafficInformation(latlng: LatLng, zoomLevel:Int) :String{
        val (lat,lng) = latlng
        val type = "AUTO"
        val version = 1

        val basicUrl = "https://apis.openapi.sk.com/tmap/traffic?"
        val requestUrl = "${basicUrl}version=${version}&trafficType=${type}&centerLat=${lat}&centerLon=${lng}&zoomLevel=${zoomLevel}&appKey=${projectKey}"

        return java.net.URL(requestUrl).readText()
    }
}
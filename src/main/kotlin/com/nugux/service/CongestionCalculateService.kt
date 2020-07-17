package com.nugux.service

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.logic.BasicCongestionCalculator
import com.nugux.model.*
import com.nugux.repository.DailySpotCongestionRepository
import com.nugux.util.TrafficLineParser
import com.nugux.util.readCsv
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.lang.Exception
import kotlin.streams.toList

@Service
class CongestionCalculateService(private val projectKey: String,
                                 private val trafficInformationService: TrafficInformationService,
                                 private val dailySpotCongestionRepository: DailySpotCongestionRepository): ILogging by LoggingImpl<CongestionCalculateService>() {
    private val basicCongestionCalculator: BasicCongestionCalculator = BasicCongestionCalculator()

    @Scheduled(cron = "0 0 12 * * ?", zone = "Asia/Seoul")
    fun updateDailySpotCongestion() {
        dailySpotCongestionRepository.saveAll(getDailySpotCongestion())
    }

    fun getDailySpotCongestion(): List<DailySpotCongestion> {
        return getSpotLatLngsFromCsv().stream()
            .map {
                DailySpotCongestion(
                    state = it.state,
                    city = it.city,
                    spotLevel = it.spotLevel,
                    congestion = getAvgCongestion(
                        it.lat,
                        it.lng,
                        if (it.spotLevel == SpotLevel.STATE) STATE_ZOOM_LEVEL else CITY_ZOOM_LEVEL
                    ),
                    lat = it.lat,
                    long = it.lng
                )
            }.toList()
    }

    private fun getSpotLatLngsFromCsv(): List<SpotLatLng> {
        return readCsv(SPOT_LAT_LONG_CSV_PATH).stream()
            .map {
                val stateCity = it[0].split(" ")
                when (stateCity.size) {
                    1 -> SpotLatLng(
                        state = stateCity[0],
                        city = null,
                        spotLevel = SpotLevel.STATE,
                        lat = it[1].toDouble(),
                        lng = it[2].toDouble()
                    )
                    2 -> SpotLatLng(
                        state = stateCity[0],
                        city = stateCity[1],
                        spotLevel = SpotLevel.CITY,
                        lat = it[1].toDouble(),
                        lng = it[2].toDouble()
                    )
                    else -> null
                }
            }.toList()
            .filterNotNull()
    }

    private fun getAvgCongestion(lat: Double, lng: Double, zoomLevel: Int): Double {
        return try {
            val latLng = LatLng(lat = lat, lng = lng)
            val trafficInformation = trafficInformationService.getTrafficInformation(latLng, zoomLevel)
            val trafficLines = TrafficLineParser().makeObjects(trafficInformation, latLng)
            basicCongestionCalculator.getCongestion(trafficLines)
        } catch (e: Exception) {
            log.error(e.message)
            0.0
        }
    }

    companion object {
        const val SPOT_LAT_LONG_CSV_PATH = "spot_lat_long_csv/spot_lat_long.csv"
        const val STATE_ZOOM_LEVEL = 12
        const val CITY_ZOOM_LEVEL = 14
    }
}
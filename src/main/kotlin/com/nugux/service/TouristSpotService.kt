package com.nugux.service

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.model.TouristSpotDTO
import com.nugux.repository.TouristSpotRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TouristSpotService(private val touristSpotRepository: TouristSpotRepository) :
    ILogging by LoggingImpl<TouristSpotService>() {

    fun get(southWestLat: Double,
            southWestLong: Double,
            northEastLat: Double,
            northEastLong: Double,
            date: Date
    ): List<TouristSpotDTO> {
        TODO()
    }
}
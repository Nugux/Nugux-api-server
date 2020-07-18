package com.nugux.service

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.logic.WeeksCongestionCalculator
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
        val touristSpotList = touristSpotRepository.findByBoundaryPosition(southWestLat, southWestLong, northEastLat, northEastLong)
        calendar.time = date
        return touristSpotList.map {
            TouristSpotDTO(id = it.id,
                name = it.name,
                shortDesc = it.description.take(30),
                lat = it.lat,
                long = it.long,
                congestion = WeeksCongestionCalculator.getWeeksCongestion(it.congestion)[DailySpotCongestionService.calendar.get(Calendar.DAY_OF_WEEK) - 1])
        }.toList()
    }

    companion object {
        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"))
    }
}
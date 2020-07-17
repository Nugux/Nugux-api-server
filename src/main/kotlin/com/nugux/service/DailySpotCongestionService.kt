package com.nugux.service

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.logic.BasicCongestionCalculator
import com.nugux.logic.WeeksCongestionCalculator
import com.nugux.model.DailySpotCongestion
import com.nugux.model.DailySpotCongestionDto
import com.nugux.model.SpotLevel
import com.nugux.repository.DailySpotCongestionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DailySpotCongestionService(private val dailySpotCongestionRepository: DailySpotCongestionRepository) :
    ILogging by LoggingImpl<DailySpotCongestionService>() {

    fun get(southWestLat: Double,
            southWestLong: Double,
            northEastLat: Double,
            northEastLong: Double,
            spotLevel: SpotLevel,
            date: Date): List<DailySpotCongestionDto> {
        val dailySpotContestions = dailySpotCongestionRepository.findByCenterAndBoundaryPositionAndSpotLevel(southWestLat, southWestLong, northEastLat, northEastLong, spotLevel)
        calendar.time = date
        return dailySpotContestions.map { DailySpotCongestionDto(
            state = it.state,
            city = it.city,
            lat = it.lat,
            long = it.long,
            congestion = WeeksCongestionCalculator.getWeeksCongestion(it.congestion)[calendar.get(Calendar.DAY_OF_WEEK) - 1]) }
            .toList()
    }

    companion object {
        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"))
    }
}
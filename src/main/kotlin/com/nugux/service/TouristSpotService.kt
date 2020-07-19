package com.nugux.service

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.logic.WeeksCongestionCalculator
import com.nugux.model.SpotCongestion
import com.nugux.model.TouristSpotDTO
import com.nugux.model.TouristSpotDetailDTO
import com.nugux.repository.SpotCongestionRepository
import com.nugux.repository.TouristSpotRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.streams.toList

@Service
class TouristSpotService(private val touristSpotRepository: TouristSpotRepository,
                         private val spotCongestionRepository: SpotCongestionRepository) :
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
                congestion = WeeksCongestionCalculator.getWeeksCongestion(it.congestion)[DailySpotCongestionService.calendar.get(Calendar.DAY_OF_WEEK) - 1],
                image = getImgById(it.id),
                premium = it.premium)
        }.toList()
    }

    fun getOneById(id: Long): TouristSpotDetailDTO {
        val touristSpot = touristSpotRepository.findById(id).get()

        return TouristSpotDetailDTO(
            id = touristSpot.id,
            name = touristSpot.name,
            address = touristSpot.address,
            description = touristSpot.description,
            congestionList = getCongestionListBySpotId(touristSpot.id).stream()
                .sorted { o1, o2 -> o1.day.compareTo(o2.day) }
                .map { it.congestion }
                .toList(),
            image = getImgById(touristSpot.id),
            lat = touristSpot.lat,
            long = touristSpot.long)
    }

    private fun getImgById(id: Long): String {
        val imgIndex = (id % 10).toInt()
        return "/img/tourist_spot_$imgIndex.jpg"
    }

    private fun getCongestionListBySpotId(spotId: Long): List<SpotCongestion> = spotCongestionRepository.findBySpotId(spotId)

    companion object {
        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"))
    }
}
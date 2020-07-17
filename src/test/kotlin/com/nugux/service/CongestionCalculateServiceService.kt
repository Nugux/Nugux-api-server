package com.nugux.service

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.model.DailySpotCongestion
import com.nugux.model.SpotLevel
import com.nugux.model.TouristSpot
import com.nugux.repository.DailySpotCongestionRepository
import com.nugux.repository.TouristSpotRepository
import com.nugux.repository.TouristSpotRepositoryTest
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.util.*
import kotlin.streams.toList

@RunWith(SpringRunner::class)
@SpringBootTest
internal class CongestionCalculateServiceService : ILogging by LoggingImpl<CongestionCalculateServiceService>() {
    @Autowired
    private lateinit var congestionCalculateService: CongestionCalculateService

    @Autowired
    private lateinit var dailySpotCongestionService: DailySpotCongestionService

    @Test
    fun `t01 create mock daily spot congestion data`() {
        log.info("create mock daily spot congestion data")
        congestionCalculateService.updateDailySpotCongestion()
    }

    @Test
    fun `t02 create mock daily spot congestion data`() {
        log.info("create mock daily spot congestion data")
        val  dailySpotCongestions = dailySpotCongestionService.get(36.0, 125.0, 38.0, 128.0, SpotLevel.STATE, Date.from(Instant.now()))
        print( dailySpotCongestions)
    }
}

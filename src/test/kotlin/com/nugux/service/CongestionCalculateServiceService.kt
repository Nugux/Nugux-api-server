package com.nugux.service

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.model.TouristSpot
import com.nugux.repository.TouristSpotRepository
import com.nugux.repository.TouristSpotRepositoryTest
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
internal class CongestionCalculateServiceService : ILogging by LoggingImpl<CongestionCalculateServiceService>() {
    @Autowired
    private lateinit var congestionCalculateService: CongestionCalculateService

    @Test
    fun `t01 create mock daily spot congestion data`() {
        log.info("create mock daily spot congestion data")
//        congestionCalculateService.updateDailySpotCongestion()
        val dailySpotCongestion = congestionCalculateService.getDailySpotCongestion()
        print(dailySpotCongestion)
    }
}

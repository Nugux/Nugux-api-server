package com.nugux.service

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.util.*
import kotlin.test.assertTrue

@RunWith(SpringRunner::class)
@SpringBootTest
internal class TouristSpotServiceTest : ILogging by LoggingImpl<TouristSpotServiceTest>() {
    @Autowired
    private lateinit var touristSpotService: TouristSpotService

    private val northEastLat: Double = 36.5

    private val southWestLat: Double = 37.5

    private val northEastLong: Double = 126.0

    private val southWestLong: Double = 128.0

    @Test
    fun `t01 validate latLng tourist get by boundary`() {
        touristSpotService.get(northEastLat = northEastLat, southWestLat = southWestLat, northEastLong = northEastLong, southWestLong =southWestLong, date = Date.from(Instant.now())).stream()
            .forEach {
                assertTrue { it.lat in northEastLat..southWestLat }
                assertTrue { it.long in northEastLong..southWestLong }
            }
    }
}
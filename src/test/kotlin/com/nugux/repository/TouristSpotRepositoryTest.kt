package com.nugux.repository

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.model.SpotCongestion
import com.nugux.model.TouristSpot
import com.nugux.util.TouristSpotJsonParser
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import kotlin.random.Random

@RunWith(SpringRunner::class)
@SpringBootTest
internal class TouristSpotRepositoryTest : ILogging by LoggingImpl<TouristSpotRepositoryTest>() {
    @Autowired
    private lateinit var touristSpotRepository: TouristSpotRepository

    @Autowired
    private lateinit var spotCongestionRepository: SpotCongestionRepository

    private val touristSpotJsonParser: TouristSpotJsonParser = TouristSpotJsonParser()

    @Test
    fun `t01 create mock tourist spot data`() {
        log.info("t01 create mock tourist spot data")

        for (fileIndex in 0..22) {
            log.info("Save file $fileIndex")

            val filePath = "/tourist_spot_json/sparql($fileIndex).json"
            val touristSpots: List<TouristSpot> = touristSpotJsonParser.makeObjects(filePath)
            touristSpots.stream().forEach{ touristSpotRepository.save(it) }
        }
    }

    @Test
    fun `t02 create mock spot congestion data`() {
        log.info("t02 create mock spot congestion data")
        val dayWeight = listOf(0.0..4.0, 0.0..4.0, 0.0..4.0, 0.0..4.0, 1.0..4.0, 1.5..4.0, 1.5..4.0)
        touristSpotRepository.findAll().stream().forEach {touristSpot ->
            dayWeight.withIndex().forEach {
                spotCongestionRepository.save(SpotCongestion(spotId = touristSpot.id, congestion = Random.nextDouble(it.value.start, it.value.endInclusive), day = it.index))
            }
        }
    }
}
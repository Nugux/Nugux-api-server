package com.nugux.repository

import com.nugux.logging.ILogging
import com.nugux.logging.LoggingImpl
import com.nugux.model.TouristSpot
import com.nugux.util.TouristSpotJsonParser
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
internal class TouristSpotRepositoryTest : ILogging by LoggingImpl<TouristSpotRepositoryTest>() {
    @Autowired
    private lateinit var touristSpotRepository: TouristSpotRepository

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
}
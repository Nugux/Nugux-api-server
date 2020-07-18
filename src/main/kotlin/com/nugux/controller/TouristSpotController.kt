package com.nugux.controller

import com.nugux.config.DAILY_CONGESTION_URL
import com.nugux.config.REST_API_V_1
import com.nugux.config.TOURIST_SPOT_URL
import com.nugux.model.DailySpotCongestionDto
import com.nugux.model.SpotLevel
import com.nugux.model.TouristSpotDTO
import com.nugux.service.DailySpotCongestionService
import com.nugux.service.TouristSpotService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(REST_API_V_1 + TOURIST_SPOT_URL)
@Api(value = REST_API_V_1 + TOURIST_SPOT_URL, tags = ["Tourist Spot"])
class TouristSpotController(private val touristSpotService: TouristSpotService) {
    @GetMapping
    @ApiOperation(value = "get tourist spot info", response = TouristSpotDTO::class, responseContainer = "List")
    fun getV1(
        @RequestParam(name = "southWestLat", required = true)
        @ApiParam(name = "southWestLat", value = "south west latitude")
        southWestLat: Double,
        @RequestParam(name = "southWestLong", required = true)
        @ApiParam(name = "southWestLong", value = "south west longitude")
        southWestLong: Double,
        @RequestParam(name = "northEastLat", required = true)
        @ApiParam(name = "northEastLat", value = "north east latitude")
        northEastLat: Double,
        @RequestParam(name = "northEastLong", required = true)
        @ApiParam(name = "northEastLong", value = "north east longitude")
        northEastLong: Double,
        @RequestParam(name = "date", required = true)
        @ApiParam(name = "date", value = "date")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        date: Date
    ): ResponseEntity<List<TouristSpotDTO>> = ResponseEntity
        .status(HttpStatus.OK)
        .body(touristSpotService.get(southWestLat, southWestLong, northEastLat, northEastLong, date))
}
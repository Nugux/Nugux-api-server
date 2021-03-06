package com.nugux.controller

import com.nugux.config.DAILY_CONGESTION_URL
import com.nugux.config.REST_API_V_1
import com.nugux.model.DailySpotCongestionDto
import com.nugux.model.SpotLevel
import com.nugux.service.DailySpotCongestionService
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
@RequestMapping(REST_API_V_1 + DAILY_CONGESTION_URL)
@Api(value = REST_API_V_1 + DAILY_CONGESTION_URL, tags = ["Daily Congestion"])
class DailySpotCongestionController(private val dailySpotCongestionService: DailySpotCongestionService) {
    @GetMapping
    @ApiOperation(value = "get daily congestion info", response = DailySpotCongestionDto::class, responseContainer = "List")
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
        @RequestParam(name = "spotLevel", required = true)
        @ApiParam(name = "spotLevel", value = "spotLevel")
        spotLevel: SpotLevel,
        @RequestParam(name = "date", required = true)
        @ApiParam(name = "date", value = "date")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        date: Date
    ): ResponseEntity<List<DailySpotCongestionDto>> = ResponseEntity
        .status(HttpStatus.OK)
        .body(dailySpotCongestionService.get(southWestLat, southWestLong, northEastLat, northEastLong, spotLevel, date))

}
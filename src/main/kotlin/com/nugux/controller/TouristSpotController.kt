package com.nugux.controller

import com.nugux.config.REST_API_V_1
import com.nugux.config.TOURIST_SPOT_URL
import com.nugux.model.SpotLevel
import com.nugux.model.TouristSpotDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(REST_API_V_1 + TOURIST_SPOT_URL)
@Api(value = REST_API_V_1 + TOURIST_SPOT_URL, tags = ["Tourist Spot"])
class TouristSpotController {
    @GetMapping
    @ApiOperation(value = "getTouristSpotInfo", response = TouristSpotDto::class)
    fun getV1(@RequestParam(name = "lat", required = true)
              @ApiParam(name = "lat", value = "latitude")
              lat: Double,
              @RequestParam(name = "long", required = true)
              @ApiParam(name = "long", value = "longitude")
              long: Double,
              @RequestParam(name = "spotLevel", required = true)
              @ApiParam(name = "spotLevel", value = "spotLevel")
              spotLevel: SpotLevel): ResponseEntity<TouristSpotDto> {
        TODO()
    }
}
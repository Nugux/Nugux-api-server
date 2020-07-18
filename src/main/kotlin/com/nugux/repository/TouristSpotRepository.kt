package com.nugux.repository

import com.nugux.model.TouristSpot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TouristSpotRepository: JpaRepository<TouristSpot, Long> {
    @Query("SELECT ts FROM TouristSpot ts WHERE :nelat <= ts.lat AND ts.lat <= :swlat AND :nelong <= ts.long AND ts.long <= :swlong")
    fun findByBoundaryPosition(@Param("swlat")southWestLat: Double,
                                                    @Param("swlong")southWestLong: Double,
                                                    @Param("nelat")northEastLat: Double,
                                                    @Param("nelong")northEastLong: Double): List<TouristSpot>
}

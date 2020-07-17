package com.nugux.repository

import com.nugux.model.DailySpotCongestion
import com.nugux.model.SpotLevel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DailySpotCongestionRepository: JpaRepository<DailySpotCongestion, Long> {
    @Query("SELECT dsc FROM DailySpotCongestion dsc WHERE :swlat <= dsc.lat AND dsc.lat <= :nelat AND :swlong <= dsc.long AND dsc.long <= :nelong AND dsc.spotLevel = :level")
    fun findByCenterAndBoundaryPositionAndSpotLevel(@Param("swlat")southWestLat: Double,
                                                    @Param("swlong")southWestLong: Double,
                                                    @Param("nelat")northEastLat: Double,
                                                    @Param("nelong")northEastLong: Double,
                                                    @Param("level")spotLevel: SpotLevel): List<DailySpotCongestion>
}

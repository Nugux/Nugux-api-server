package com.nugux.repository

import com.nugux.model.DailySpotCongestion
import com.nugux.model.SpotLevel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DailySpotCongestionRepository: JpaRepository<DailySpotCongestion, Long> {
    @Query("SELECT dsc FROM DailySpotCongestion dsc WHERE :nelat <= dsc.lat AND dsc.lat <= :swlat AND :nelong <= dsc.long AND dsc.long <= :swlong AND dsc.spotLevel = :level")
    fun findByBoundaryPositionAndSpotLevel(@Param("swlat")southWestLat: Double,
                                           @Param("swlong")southWestLong: Double,
                                           @Param("nelat")northEastLat: Double,
                                           @Param("nelong")northEastLong: Double,
                                           @Param("level")spotLevel: SpotLevel): List<DailySpotCongestion>
}

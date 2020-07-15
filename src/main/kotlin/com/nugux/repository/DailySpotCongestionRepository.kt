package com.nugux.repository

import com.nugux.model.DailySpotCongestion
import org.springframework.data.jpa.repository.JpaRepository

interface DailySpotCongestionRepository: JpaRepository<DailySpotCongestion, Long> {
}

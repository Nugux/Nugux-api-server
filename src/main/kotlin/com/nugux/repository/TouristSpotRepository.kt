package com.nugux.repository

import com.nugux.model.TouristSpot
import org.springframework.data.jpa.repository.JpaRepository

interface TouristSpotRepository: JpaRepository<TouristSpot, Long> {
}

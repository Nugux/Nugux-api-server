package com.nugux.logic

import com.nugux.model.TrafficLine

interface CongestionCalculator {
    fun getCongestion (trafficLines: List<TrafficLine>) : Double
}
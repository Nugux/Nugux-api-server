package com.nugux.logic

import com.nugux.model.TrafficLine

class BasicCongestionCalculator : CongestionCalculator {
    override fun getCongestion(trafficLines: List<TrafficLine>) :Double{
        return trafficLines.map {it.congestion}.average()
    }
}
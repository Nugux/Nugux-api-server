package com.nugux.logic

import com.nugux.model.TrafficLine

class BasicCongestionCalculator : CongestionCalculator {
    override fun getCongestion(trafficLines: List<TrafficLine>) :Double{
        val values = trafficLines.map { it.congestion as Double}
                                 .sortedBy { it }

        return values.getOrElse(values.count()/2) {values.average()}
    }
}
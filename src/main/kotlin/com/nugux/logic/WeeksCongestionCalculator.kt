package com.nugux.logic

class WeeksCongestionCalculator {
    companion object {
        fun getWeeksCongestion (baseCongestion:Double): List<Double> {
            val congestionOfWeek  =  listOf(baseCongestion,baseCongestion,baseCongestion,baseCongestion,baseCongestion,baseCongestion,baseCongestion)

            val weatherOfWeek =  listOf(40.0,90.0,10.0,30.0,40.0,50.0,50.0)
            val averageCongestionOfWeek = listOf(4.0,1.0,2.0,1.0,2.0,3.0,4.0)

            val inverseWeatherOfWeek = weatherOfWeek.map { 100 -it }
            val normalInverseWeather = customNormalization (inverseWeatherOfWeek)
            val normalAverage = customNormalization(averageCongestionOfWeek)
            val normalOfWeek = normalInverseWeather.zip(normalAverage).map { (x,y) -> (x+y)/2 }

            return normalOfWeek.zip(congestionOfWeek).map { (x,y) -> (x+y)/2 }
        }

        private fun customNormalization (data:List<Double>) : List<Double> {
            val min = data.min()!!
            val max = data.max()!!

            return  data.map { (((it-min) / (max!! - min!!))/1.25 + 0.2)*5} ?: listOf(1.0,1.0,1.0,1.0,1.0,1.0,1.0)

        }
    }
}

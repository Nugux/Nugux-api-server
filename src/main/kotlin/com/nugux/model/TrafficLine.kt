package com.nugux.model

data class LatLng(
    val lat: Double,
    val lng: Double
)

data class TrafficLine(
    val roadId: Int,
    val congestion: Int, //0: 미정의, 1~4 : 1일수록 덜 혼잡
    val speed: Double,
    val center: LatLng,
    val target: LatLng
)
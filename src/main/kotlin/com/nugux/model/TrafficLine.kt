package com.nugux.model

enum class Direction {
    Forward,
    Backward
}

data class LatLng(
    val lat: Double,
    val lng: Double
)

data class TrafficLine(
    val name: String,
    val congestion: Int, //0: 미정의, 1~4 : 1일수록 덜 혼잡
    val speed: Double,
    val direction: Direction,
    val distanceFromCenter: Double, // Km
    val center: LatLng,
    val target: LatLng
)
package com.nugux.model

data class SpotLatLng(
    val state: String,
    val city: String?,
    val spotLevel: SpotLevel,
    val lat: Double,
    val lng: Double
)

package com.nugux.model

import javax.persistence.*

data class TouristSpotDTO(
    val id: Long,
    val name: String,
    val shortDesc: String,
    val lat: Double,
    val long: Double,
    val congestion: Double,
    val image: String,
    val premium: Boolean
)

data class TouristSpotDetailDTO(
    val id: Long,
    val name: String,
    val address: String,
    val description: String,
    val congestionList: List<Double>,
    val image: String,
    val lat: Double,
    val long: Double
)


enum class SpotLevel(val value: Int) {
    STATE(0), CITY(1);

    companion object {
        private val values = values();
        fun getByValue(value: Int) = values.firstOrNull { it.value == value }
    }
}

@Entity
@Table(name = "tourist_spots", schema = "public")
data class TouristSpot(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = COL_ID)
    var id: Long = 0,

    @Column(name = COL_NAME, nullable = false)
    val name: String,

    @Column(name = COL_ADDRESS)
    val address: String,

    @Column(name = COL_POSTAL_CODE)
    val postalCode: String,

    @Column(name = COL_DESCRIPTION)
    val description: String,

    @Column(name = COL_LAT)
    val lat: Double,

    @Column(name = COL_LONG)
    val long: Double,

    @Column(name = COL_CONGESTION)
    val congestion: Double,

    @Column(name = COL_PREMIUM)
    val premium: Boolean
) {
    companion object {
        const val COL_ID: String = "id"
        const val COL_NAME: String = "name"
        const val COL_ADDRESS: String = "address"
        const val COL_POSTAL_CODE: String = "postal_code"
        const val COL_DESCRIPTION: String = "description"
        const val COL_LAT: String = "lat"
        const val COL_LONG: String = "long"
        const val COL_CONGESTION: String = "congestion"
        const val COL_PREMIUM: String = "premium"
    }
}

@Entity
@Table(name = "spot_congestions", schema = "public")
data class SpotCongestion(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = COL_ID)
    var id: Long = 0,

    @Column(name = COL_SPOT_ID)
    val spotId: Long,

    @Column(name = COL_CONGESTION)
    val congestion: Double,

    @Column(name = COL_DAY)
    val day: Int
) {
    companion object {
        const val COL_ID: String = "id"
        const val COL_SPOT_ID: String = "spot_id"
        const val COL_CONGESTION: String = "congestion"
        const val COL_DAY: String = "day"
    }
}

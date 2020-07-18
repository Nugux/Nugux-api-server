package com.nugux.model

import javax.persistence.*

data class TouristSpotDTO(
    val id: Long,
    val name: String,
    val shortDesc: String,
    val lat: Double,
    val long: Double,
    val congestion: Double
)

data class TouristSpotDetailDTO(
    val id: Long,
    val name: String,
    val address: String,
    val description: String,
    val congestionList: List<Double>,
    val image: ByteArray
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
    val congestion: Double
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
    }

    fun getStateFromAddress() = address.split(" ")[0]

    fun getCityFromAddress() = address.split(" ")[1]
}

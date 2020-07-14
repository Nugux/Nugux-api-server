package com.nugux.model

import com.vividsolutions.jts.geom.Geometry
import com.vividsolutions.jts.geom.Point
import org.hibernate.annotations.Type
import javax.persistence.*

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
    val long: Double
) {

    companion object {
        const val COL_ID: String = "id"
        const val COL_NAME: String = "name"
        const val COL_ADDRESS: String = "address"
        const val COL_POSTAL_CODE: String = "postal_code"
        const val COL_DESCRIPTION: String = "description"
        const val COL_LAT: String = "lat"
        const val COL_LONG: String = "long"
    }
}

package com.nugux.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "daily_spot_congestions", schema = "public", uniqueConstraints = [UniqueConstraint(columnNames = ["state", "city", "spot_level"])])
data class DailySpotCongestion(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = COL_ID)
    var id: Long = 0,

    @Column(name = COL_STATE, nullable = false)
    val state: String,

    @Column(name = COL_CITY, nullable = true)
    val city: String?,

    @Column(name = COL_CONGESTION, nullable = true)
    val congestion: Double,

    @Column(name = COL_SPOT_LEVEL, nullable = true)
    val spotLevel: SpotLevel,

    @Column(name = COL_UPDATE_DATE, nullable = true)
    val updateDate: LocalDate = LocalDate.now()
){
    companion object {
        const val COL_ID: String = "id"
        const val COL_STATE: String = "state"
        const val COL_CITY: String = "city"
        const val COL_CONGESTION: String = "congestion"
        const val COL_SPOT_LEVEL: String = "spot_level"
        const val COL_UPDATE_DATE: String = "update_date"
    }
}


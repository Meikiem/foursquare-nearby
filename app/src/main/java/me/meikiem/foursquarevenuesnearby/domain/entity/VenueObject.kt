package me.meikiem.foursquarevenuesnearby.domain.entity

import java.io.Serializable

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

data class VenueObject(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double
):Serializable
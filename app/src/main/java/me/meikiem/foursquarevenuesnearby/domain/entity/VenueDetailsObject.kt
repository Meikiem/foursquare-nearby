package me.meikiem.foursquarevenuesnearby.domain.entity

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */


data class VenueDetailsObject(
    val photo: String,
    val phone: String,
    val description: String,
    val id: String,
    val likes: String,
    val address: String,
    val name: String,
    val rating: Double
)
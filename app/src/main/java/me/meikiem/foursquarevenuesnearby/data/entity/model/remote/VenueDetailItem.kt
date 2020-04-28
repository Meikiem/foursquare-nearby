package me.meikiem.foursquarevenuesnearby.data.entity.model.remote

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

data class VenueDetailItem(
    val bestPhoto: BestPhoto,
    val contact: Contact,
    val description: String,
    val id: String,
    val likes: Likes,
    val location: Location,
    val name: String,
    val rating: Double
) {
    data class BestPhoto(
        val prefix: String,
        val suffix: String,
        val visibility: String
    )

    data class Contact(
        val formattedPhone: String
    )

    data class Likes(
        val summary: String
    )

    data class Location(
        val address: String
    )
}

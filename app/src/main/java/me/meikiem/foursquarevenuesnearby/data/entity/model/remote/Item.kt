package me.meikiem.foursquarevenuesnearby.data.entity.model.remote

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

data class Item(val venue: Venue){
    data class Venue(
        val id: String,
        val name: String,
        val location: Location
    ){
        data class Location(
            val lat: Double
            , val lng: Double
        )
    }
}
package me.meikiem.foursquarevenuesnearby.data.entity.model.remote

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

sealed class VenuesList {
    data class Response(
        val response:Result

    ){
        data class Result(
            val groups: List<Group>
        )
    }
}
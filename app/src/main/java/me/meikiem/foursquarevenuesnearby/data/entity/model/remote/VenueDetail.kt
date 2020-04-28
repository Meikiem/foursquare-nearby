package me.meikiem.foursquarevenuesnearby.data.entity.model.remote


sealed class VenueDetail{
    data class Response(
        val response:Result
    ){
        data class Result(
            val venue:VenueDetailItem
        )
    }
}

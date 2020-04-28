package me.meikiem.foursquarevenuesnearby.data.repository.venue

import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueEntity

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */
interface VenueRepository {
    fun getVenuesNearby(placeName: String, limit:Int, offset:Int): Single<List<VenueEntity>>
}
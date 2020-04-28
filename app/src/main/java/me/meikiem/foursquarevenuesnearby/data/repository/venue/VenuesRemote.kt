package me.meikiem.foursquarevenuesnearby.data.repository.venue

import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.Group

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */
interface VenuesRemote {

    fun getVenuesNearBy(placeName: String, limit:Int, offset:Int): Single<List<Group>>
}
package me.meikiem.foursquarevenuesnearby.data.repository.venue

import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailEntity

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

interface VenueDetailsRemote {

    fun getVenueDetails(venueId: String): Single<VenueDetailEntity>
}
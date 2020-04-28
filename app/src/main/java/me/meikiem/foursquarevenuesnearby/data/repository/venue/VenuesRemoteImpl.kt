package me.meikiem.foursquarevenuesnearby.data.repository.venue

import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.BuildConfig
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.Group
import me.meikiem.foursquarevenuesnearby.data.network.VenueApi
import me.meikiem.foursquarevenuesnearby.data.repository.getTodayDateFormatted
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

class VenuesRemoteImpl @Inject constructor(
    private val venueApi: VenueApi
) : VenuesRemote {
    override fun getVenuesNearBy(
        placeName: String,
        limit:Int,
        offset:Int
    ): Single<List<Group>> {
        return venueApi.getVenuesNearBy(
            placeName,
            offset,
            limit,
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET,
            getTodayDateFormatted()
        ) .map {
            it.response.groups
        }
    }
}
package me.meikiem.foursquarevenuesnearby.data.store

import io.reactivex.Completable
import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailEntity
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsCache
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsDataStore
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

class VenueDetailsCacheDataStore @Inject constructor(
    private val venueDetailsCache: VenueDetailsCache
):VenueDetailsDataStore{
    override fun getVenueDetails(venueId: String): Single<VenueDetailEntity> {
        return venueDetailsCache.getVenueDetails(venueId)
    }

    override fun saveVenueDetails(venue: VenueDetailEntity): Completable {
        return venueDetailsCache.saveVenueDetail(venue)
    }

    override fun clearVenueDetails(venueId: String): Completable {
        return venueDetailsCache.clearVenueDetail(venueId)
    }

    override fun setLastCacheInfo(venueId: String): Completable {
        return venueDetailsCache.setLastCacheInfo(System.currentTimeMillis(), venueId)
    }

}
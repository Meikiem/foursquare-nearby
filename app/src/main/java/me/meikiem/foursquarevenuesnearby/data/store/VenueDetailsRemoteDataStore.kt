package me.meikiem.foursquarevenuesnearby.data.store

import io.reactivex.Completable
import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailEntity
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsDataStore
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsRemote
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

class VenueDetailsRemoteDataStore @Inject constructor(
    private val venueDetailsRemote: VenueDetailsRemote
):VenueDetailsDataStore{
    override fun getVenueDetails(venueId: String): Single<VenueDetailEntity> {
        return venueDetailsRemote.getVenueDetails(venueId)
    }

    override fun saveVenueDetails(venue: VenueDetailEntity): Completable {
        throw UnsupportedOperationException("Saving venues isn't supported here...")    }

    override fun clearVenueDetails(venueId:String): Completable {
        throw UnsupportedOperationException("Clearing venues isn't supported here...")
    }

    override fun setLastCacheInfo(venueId: String): Completable  {
        throw UnsupportedOperationException("Setting venues isn't supported here...")
    }

}
package me.meikiem.foursquarevenuesnearby.data.store

import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsDataStore
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

open class VenueDetailsDataStoreFactory @Inject constructor(
    private val venueDetailsCacheDataStore: VenueDetailsCacheDataStore,
    private val venueDetailsRemoteDataStore: VenueDetailsRemoteDataStore
){
    open fun getDataStore(connectivityAvailable:Boolean, venueDetailsCached: Boolean, cacheExpired: Boolean): VenueDetailsDataStore {
        return if (!connectivityAvailable || (venueDetailsCached && !cacheExpired)) {
            venueDetailsCacheDataStore
        } else {
            venueDetailsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): VenueDetailsDataStore {
        return venueDetailsCacheDataStore
    }
}
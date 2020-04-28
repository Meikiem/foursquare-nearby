package me.meikiem.foursquarevenuesnearby.data.store

import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesDataStore
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

open class VenuesDataStoreFactory @Inject constructor(
    private val venuesCacheDataStore: VenuesCacheDataStore,
    private val venuesRemoteDataStore: VenuesRemoteDataStore
) {

    open fun getDataStore(venuesCached: Boolean, cacheExpired: Boolean): VenuesDataStore {
        return if (venuesCached && !cacheExpired) {
            venuesCacheDataStore
        } else {
            venuesRemoteDataStore
        }
    }

    open fun getCacheDataStore(): VenuesDataStore {
        return venuesCacheDataStore
    }

}
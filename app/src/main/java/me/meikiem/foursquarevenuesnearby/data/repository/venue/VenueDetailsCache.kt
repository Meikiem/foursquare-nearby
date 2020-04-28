package me.meikiem.foursquarevenuesnearby.data.repository.venue

import io.reactivex.Completable
import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailEntity

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */
interface VenueDetailsCache {
    fun clearVenueDetail(venueId: String): Completable
    fun saveVenueDetail(venue: VenueDetailEntity): Completable
    fun getVenueDetails(venueId: String): Single<VenueDetailEntity>
    fun areVenueDetailCached(venueId: String): Single<Boolean>
    fun setLastCacheInfo(lastUpdateTime: Long, venueId: String): Completable
    fun isVenueDetailCacheExpired(venueId: String): Single<Boolean>
}
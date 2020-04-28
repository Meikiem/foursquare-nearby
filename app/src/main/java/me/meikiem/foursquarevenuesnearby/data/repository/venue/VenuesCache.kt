package me.meikiem.foursquarevenuesnearby.data.repository.venue

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.reactivex.Completable
import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueEntity
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */
interface VenuesCache {
    fun clearVenuesNearby(): Completable
    fun saveVenuesNearby(nearPlace:String, venues: List<VenueEntity>): Completable
    fun getVenuesNearby(nearPlace:String): Single<List<VenueEntity>>
    fun areVenuesNearbyCached(placeName: String): Single<Boolean>
    fun setLastCacheInfo(lastUpdateTime: Long, placeName: String): Completable
    fun isVenuesNearbyCacheExpired(): Single<Boolean>
    fun getVenuesPagingNearBy(nearPlace: String): LiveData<PagedList<VenueObject>>
}
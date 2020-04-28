package me.meikiem.foursquarevenuesnearby.data.store

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.reactivex.Completable
import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueEntity
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesCache
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesDataStore
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import javax.inject.Inject


/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

open class VenuesCacheDataStore @Inject constructor(
    private val venuesCache: VenuesCache
) : VenuesDataStore {

    override fun getVenuesNearby(placeName: String, limit:Int, offset:Int): Single<List<VenueEntity>> {
        return venuesCache.getVenuesNearby(placeName)
    }

    override fun saveVenuesNearby(
        placeName: String,
        limit: Int,
        offset: Int,
        venues: List<VenueEntity>
    ): Completable {
        return venuesCache.saveVenuesNearby(placeName, venues)
            .andThen(venuesCache.setLastCacheInfo(System.currentTimeMillis(), placeName))
    }

    override fun clearVenuesNearby(): Completable {
        return venuesCache.clearVenuesNearby()
    }

    override fun getVenuesPagingNearby(nearPlace:String): LiveData<PagedList<VenueObject>> {
        return venuesCache.getVenuesPagingNearBy(nearPlace);

    }




}


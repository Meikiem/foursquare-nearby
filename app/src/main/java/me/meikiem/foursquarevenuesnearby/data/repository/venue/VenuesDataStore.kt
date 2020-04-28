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
interface VenuesDataStore {

    fun getVenuesNearby(placeName: String, limit:Int, offset:Int): Single<List<VenueEntity>>

    fun saveVenuesNearby(placeName: String, limit:Int, offset:Int, venues: List<VenueEntity>): Completable

    fun clearVenuesNearby(): Completable

    fun getVenuesPagingNearby(nearPlace:String): LiveData<PagedList<VenueObject>>

}
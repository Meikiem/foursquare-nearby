package me.meikiem.foursquarevenuesnearby.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueDetailsObject
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

interface VenueDomainRepository {

    fun getVenuesNearby(placeName: String, limit:Int, offset:Int): Single<List<VenueObject>>
    fun getVenuesPagingNearby(nearPlace:String): LiveData<PagedList<VenueObject>>
    fun getVenueDetails(connectivityAvailable:Boolean, venueId: String): Single<VenueDetailsObject>
}
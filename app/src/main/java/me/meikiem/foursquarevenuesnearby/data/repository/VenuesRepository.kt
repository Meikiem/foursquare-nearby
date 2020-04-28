package me.meikiem.foursquarevenuesnearby.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.meikiem.foursquarevenuesnearby.data.store.VenuesCacheDataStore
import me.meikiem.foursquarevenuesnearby.data.store.VenuesPaginFactory
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import me.meikiem.foursquarevenuesnearby.domain.executer.PostExecutionThread
import me.meikiem.foursquarevenuesnearby.domain.executer.ThreadExecutor
import me.meikiem.foursquarevenuesnearby.domain.repository.VenueDomainRepository
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/25/20.
 */

class VenuesRepository @Inject constructor(
    private val venuesCacheDataStore: VenuesCacheDataStore,
    private val venuesDomainRepository: VenueDomainRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    fun observePagedVenues(
        connectivityAvailable: Boolean,
        latLng:String,
        isCachedAndNotExpired:Boolean
    ): LiveData<PagedList<VenueObject>> {
        return if(!connectivityAvailable || isCachedAndNotExpired)
            observeLocalPagedVenues(latLng)
        else
            observeRemotePagedVenues(latLng)
    }



    fun observeLocalPagedVenues(nearPlace:String): LiveData<PagedList<VenueObject>> {
        return venuesCacheDataStore.getVenuesPagingNearby(nearPlace)
    }

    fun observeRemotePagedVenues(latLng: String)
            : LiveData<PagedList<VenueObject>> {

        val dataSourceFactory = VenuesPaginFactory(
            venuesDomainRepository,
            threadExecutor,
            postExecutionThread,
            latLng
        )
        return LivePagedListBuilder(
            dataSourceFactory,
            VenuesPaginFactory.pagedListConfig()
        ).build()
    }
}
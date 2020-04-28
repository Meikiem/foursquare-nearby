package me.meikiem.foursquarevenuesnearby.data.store

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesPagingRemote
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import me.meikiem.foursquarevenuesnearby.domain.executer.PostExecutionThread
import me.meikiem.foursquarevenuesnearby.domain.executer.ThreadExecutor
import me.meikiem.foursquarevenuesnearby.domain.repository.VenueDomainRepository

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/20/20.
 */

class VenuesPaginFactory(
    private val venuesDomainRepository: VenueDomainRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    private val latLng:String
): DataSource.Factory<Int, VenueObject>(){

    var mutableVenuesPagingRemote = MutableLiveData<VenuesPagingRemote>()

    override fun create(): DataSource<Int, VenueObject> {
        val venuesPagingRemote = VenuesPagingRemote(
            venuesDomainRepository,
            threadExecutor,
            postExecutionThread,
            latLng)
        mutableVenuesPagingRemote.postValue(venuesPagingRemote)
        return venuesPagingRemote
    }

    fun getMutableLiveData():MutableLiveData<VenuesPagingRemote>{
        return mutableVenuesPagingRemote
    }


    companion object {
        private const val PAGE_SIZE = 10

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }



}
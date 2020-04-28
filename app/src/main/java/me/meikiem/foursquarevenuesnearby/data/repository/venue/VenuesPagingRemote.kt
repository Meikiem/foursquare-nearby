package me.meikiem.foursquarevenuesnearby.data.repository.venue

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.schedulers.Schedulers
import me.meikiem.foursquarevenuesnearby.data.repository.NetworkState
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import me.meikiem.foursquarevenuesnearby.domain.executer.PostExecutionThread
import me.meikiem.foursquarevenuesnearby.domain.executer.ThreadExecutor
import me.meikiem.foursquarevenuesnearby.domain.repository.VenueDomainRepository

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/20/20.
 */

class VenuesPagingRemote(
    private val venuesDomainRepository: VenueDomainRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    private val latLng:String
    ) : PageKeyedDataSource<Int, VenueObject>() {

    private var i: Int = 0
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()
    private var retry: (() -> Any)? =null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.invoke()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, VenueObject>
    ) {

        initialLoad.postValue(NetworkState.LOADING)
        fetchData {
            callback.onResult(it, null, i)
            retry = {
                loadInitial(params, callback)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, VenueObject>) {
        i += 10

        networkState.postValue(NetworkState.LOADING)
        fetchData {
            callback.onResult(it, params.key + 1)
            retry = {
                loadAfter(params, callback)
            }
        }

    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, VenueObject>) {
    }


    fun fetchData(callback: (List<VenueObject>) -> Unit){
        venuesDomainRepository.getVenuesNearby(latLng!!, 10, i)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler())
            .subscribe({
                networkState.postValue(NetworkState.LOADED)
                callback(it)
            }, {
                it.printStackTrace()
                networkState.postValue(NetworkState.error("Network Error"))
            })
    }

}
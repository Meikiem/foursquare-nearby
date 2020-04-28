package me.meikiem.foursquarevenuesnearby.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/20/20.
 */

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<NetworkState>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val msg: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        val FAILED = NetworkState(Status.FAILED)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}

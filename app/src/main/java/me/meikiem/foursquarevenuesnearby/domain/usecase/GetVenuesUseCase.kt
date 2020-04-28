package me.meikiem.foursquarevenuesnearby.domain.usecase

import androidx.lifecycle.LiveData
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesCache
import me.meikiem.foursquarevenuesnearby.domain.executer.PostExecutionThread
import me.meikiem.foursquarevenuesnearby.domain.executer.ThreadExecutor
import me.meikiem.foursquarevenuesnearby.result.Result
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

open class GetVenuesUseCase @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    private val venuesCache: VenuesCache
) : VenuesUseCase<String, Boolean>() {
    override fun execute(
        connectivityAvailable: Boolean,
        latLng: String
    ): LiveData<Result<Boolean>> {
        result.postValue(Result.Loading)
        Single.zip(
            venuesCache.areVenuesNearbyCached(latLng),
            venuesCache.isVenuesNearbyCacheExpired(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                Pair(areCached, isExpired)
            }
        ).flatMap { Single.just(it.first && !it.second) }.subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler())
            .subscribe({
                result.postValue(Result.Success(it))
            }, {
                it.printStackTrace()
                result.postValue(Result.Error(Exception(it)))
            })
        return result
    }

}

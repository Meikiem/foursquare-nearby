package me.meikiem.foursquarevenuesnearby.domain.usecase

import androidx.lifecycle.LiveData
import io.reactivex.schedulers.Schedulers
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueDetailsObject
import me.meikiem.foursquarevenuesnearby.domain.executer.PostExecutionThread
import me.meikiem.foursquarevenuesnearby.domain.executer.ThreadExecutor
import me.meikiem.foursquarevenuesnearby.domain.repository.VenueDomainRepository
import me.meikiem.foursquarevenuesnearby.result.Result
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */
open class GetVenueDetailsUseCase @Inject constructor(
    private val venuesDomainRepository: VenueDomainRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
): VenueDetailsUseCase<String, VenueDetailsObject>() {
    override fun execute(connectivityAvailable:Boolean, venueId: String): LiveData<Result<VenueDetailsObject>> {
        result.postValue(Result.Loading)

        venuesDomainRepository.getVenueDetails(connectivityAvailable, venueId)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler())
            .subscribe({
                result.postValue(
                    Result.Success(it))
            }, {
                it.printStackTrace()
                result.postValue(
                    Result.Error(Exception(it)))
            })

        return result
    }
}
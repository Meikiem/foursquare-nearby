package me.meikiem.foursquarevenuesnearby.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.meikiem.foursquarevenuesnearby.result.Result

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */
abstract class VenueDetailsUseCase<in P, R> {
    protected val result = MutableLiveData<Result<R>>()

    abstract fun execute(connectivityAvailable:Boolean, venueId:String):LiveData<Result<R>>
}
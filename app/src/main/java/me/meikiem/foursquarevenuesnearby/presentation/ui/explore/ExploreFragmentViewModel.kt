package me.meikiem.foursquarevenuesnearby.presentation.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.meikiem.foursquarevenuesnearby.data.repository.VenuesRepository
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import me.meikiem.foursquarevenuesnearby.domain.usecase.GetVenuesUseCase
import javax.inject.Inject

class ExploreFragmentViewModel @Inject constructor(
    private val venuesRepository: VenuesRepository,
    private val getVenuesUseCase: GetVenuesUseCase
) : ViewModel() {
    private val query = MutableLiveData<String>("35.7638941,51.400064")// A DEFAULT VALUE TO SHOW A LIST

    var connectivityAvailable: Boolean = false
    lateinit var venues : LiveData<PagedList<VenueObject>>



    fun search(latLng:String , isCachedAndNotExpired:Boolean) {
        venues = venuesRepository.observePagedVenues(
            connectivityAvailable, latLng, isCachedAndNotExpired
        )
    }

    fun isCachedAndNotExpired(latLng:String)= getVenuesUseCase.execute(connectivityAvailable, latLng)


    fun setLocation(latLng: String){
        query.postValue(latLng)
    }

    fun isVenuesInitialized():Boolean{
        return this::venues.isInitialized
    }


}



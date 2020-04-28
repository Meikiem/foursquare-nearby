package me.meikiem.foursquarevenuesnearby.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.meikiem.foursquarevenuesnearby.domain.usecase.GetVenueDetailsUseCase
import me.meikiem.foursquarevenuesnearby.presentation.extension.map
import me.meikiem.foursquarevenuesnearby.result.Result
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor(
    private val getVenueDetailsUseCase: GetVenueDetailsUseCase
) : ViewModel() {
    val venueId = MutableLiveData<String>()
    var connectivityAvailable: Boolean = false

    fun setVenueId(venueId: String) {
        this.venueId.value = venueId
    }

    fun fetchDetails() = getVenueDetailsUseCase.execute(connectivityAvailable, venueId.value!!).map {
        if(it is Result.Success) it.data else null
    }

}

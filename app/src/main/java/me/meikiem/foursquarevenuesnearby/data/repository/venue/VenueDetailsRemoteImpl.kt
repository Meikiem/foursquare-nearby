package me.meikiem.foursquarevenuesnearby.data.repository.venue

import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.BuildConfig
import me.meikiem.foursquarevenuesnearby.data.entity.model.VenueDetailApiResponseModelMapper
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailEntity
import me.meikiem.foursquarevenuesnearby.data.network.VenueApi
import me.meikiem.foursquarevenuesnearby.data.repository.getTodayDateFormatted
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

class VenueDetailsRemoteImpl @Inject constructor(
    private val venueApi: VenueApi,
    private val venueDetailApiResponseModelMapper: VenueDetailApiResponseModelMapper
):VenueDetailsRemote{
    override fun getVenueDetails(
        venueId: String
    ): Single<VenueDetailEntity> {
        return venueApi.getVenueDetails(
            venueId,
            BuildConfig.CLIENT_ID,
            BuildConfig.CLIENT_SECRET,
            getTodayDateFormatted()
        ).map { venueDetailApiResponseModelMapper.mapFromApiResponseModel(it.response.venue)}
    }

}
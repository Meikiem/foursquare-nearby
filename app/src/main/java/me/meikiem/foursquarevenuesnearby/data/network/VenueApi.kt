package me.meikiem.foursquarevenuesnearby.data.network

import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetail
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenuesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/17/20.
 */
interface VenueApi {

    @GET("venues/explore")
    fun getVenuesNearBy(
        @Query("ll") placeName :String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("v") date: String
    ):Single<VenuesList.Response>

    @GET("venues/{id}")
    fun getVenueDetails(
        @Path("id") id: String?,
        @Query("client_id") clientId: String?,
        @Query("client_secret") clientSecret: String?,
        @Query("v") version: String?
    ): Single<VenueDetail.Response>


}
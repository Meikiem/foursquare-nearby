package me.meikiem.foursquarevenuesnearby.di.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import me.meikiem.foursquarevenuesnearby.data.network.NetworkFactory
import me.meikiem.foursquarevenuesnearby.data.network.VenueApi
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsRemote
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsRemoteImpl
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesRemote
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesRemoteImpl

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Module
abstract class NetworkModule {
/*    @Provides
    @Reusable
    fun postApi(networkFactory: NetworkFactory) =
        networkFactory.create(VenueApi::class.java)*/

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun postApi(networkFactory: NetworkFactory): VenueApi {
            return networkFactory.create(VenueApi::class.java)
        }
    }

    @Binds
    abstract fun bindVenuesCache(venuesRemote: VenuesRemoteImpl): VenuesRemote

    @Binds
    abstract fun bindVenueDetailsCache(venueDetailsRemote: VenueDetailsRemoteImpl): VenueDetailsRemote
}
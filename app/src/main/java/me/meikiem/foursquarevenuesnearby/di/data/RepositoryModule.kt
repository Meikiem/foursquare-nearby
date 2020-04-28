package me.meikiem.foursquarevenuesnearby.di.data

import dagger.Binds
import dagger.Module
import me.meikiem.foursquarevenuesnearby.data.repository.VenuesRemoteRepositoryImpl
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueRepository

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun getVenueRepository(implementation: VenuesRemoteRepositoryImpl): VenueRepository

}
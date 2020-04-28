package me.meikiem.foursquarevenuesnearby.di.data

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.meikiem.foursquarevenuesnearby.data.cache.db.VenueDetailsCachedImpl
import me.meikiem.foursquarevenuesnearby.data.cache.db.VenuesCacheImpl
import me.meikiem.foursquarevenuesnearby.data.cache.db.database.AppDatabase
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsCache
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesCache

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindVenuesCache(venuesCache: VenuesCacheImpl): VenuesCache

    @Binds
    abstract fun bindVenueDetailsCache(venueDetailsCache: VenueDetailsCachedImpl): VenueDetailsCache


}
package me.meikiem.foursquarevenuesnearby.di.data

import dagger.Binds
import dagger.Module
import me.meikiem.foursquarevenuesnearby.data.repository.VenuesDataRepository
import me.meikiem.foursquarevenuesnearby.domain.repository.VenueDomainRepository

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: VenuesDataRepository): VenueDomainRepository

}
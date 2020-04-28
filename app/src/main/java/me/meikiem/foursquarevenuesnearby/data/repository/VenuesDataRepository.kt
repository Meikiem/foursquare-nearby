package me.meikiem.foursquarevenuesnearby.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import me.meikiem.foursquarevenuesnearby.data.mapper.VenueDetailsMapper
import me.meikiem.foursquarevenuesnearby.data.mapper.VenueMapper
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsCache
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesCache
import me.meikiem.foursquarevenuesnearby.data.store.VenueDetailsDataStoreFactory
import me.meikiem.foursquarevenuesnearby.data.store.VenuesCacheDataStore
import me.meikiem.foursquarevenuesnearby.data.store.VenuesRemoteDataStore
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueDetailsObject
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import me.meikiem.foursquarevenuesnearby.domain.repository.VenueDomainRepository
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

class VenuesDataRepository @Inject constructor(
    private val mapper: VenueMapper,
    private val detailMapper: VenueDetailsMapper,
    private val detailsCache: VenueDetailsCache,
    private val venuesRemoteDataStore: VenuesRemoteDataStore,
    private val venuesCacheDataStore: VenuesCacheDataStore,
    private val detailStoreFactory: VenueDetailsDataStoreFactory,
    private val cache:VenuesCache
) : VenueDomainRepository {

    override fun getVenuesNearby(placeName: String, limit:Int, offset:Int): Single<List<VenueObject>> {
        val response:Single<List<VenueObject>>

        response = venuesRemoteDataStore.getVenuesNearby(placeName, limit, offset).flatMap { venueEntities ->
            Single.zip(
                cache.areVenuesNearbyCached(placeName),
                cache.isVenuesNearbyCacheExpired(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                }
            ).map {
                if(it.first && it.second)
                    venuesCacheDataStore.clearVenuesNearby()
            }
            venuesCacheDataStore.saveVenuesNearby(placeName, limit, offset, venueEntities)
                .andThen(Single.just(venueEntities))
        }.map { venueEntity ->
            venueEntity.map {
                mapper.mapFromEntity(it)
            }
        }
        return response
    }

    override fun getVenuesPagingNearby(nearPlace:String): LiveData<PagedList<VenueObject>> {

        return venuesCacheDataStore.getVenuesPagingNearby(nearPlace)
    }

    override fun getVenueDetails(connectivityAvailable:Boolean,venueId: String): Single<VenueDetailsObject> {
        val response:Single<VenueDetailsObject>
        response = Single.zip(
            detailsCache.areVenueDetailCached(venueId),
            detailsCache.isVenueDetailCacheExpired(venueId),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                Pair(areCached, isExpired)
            }
        ).flatMap {
            detailStoreFactory.getDataStore(connectivityAvailable, it.first, it.second).getVenueDetails(venueId)
        }.map { venueDetailsEntity ->

            detailStoreFactory.getCacheDataStore().clearVenueDetails(venueId)
            detailStoreFactory.getCacheDataStore().saveVenueDetails(venueDetailsEntity).blockingAwait()
            detailStoreFactory.getCacheDataStore().setLastCacheInfo(venueId).blockingAwait()
            detailMapper.mapFromEntity(venueDetailsEntity)

        }

        return response
    }



}
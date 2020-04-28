package me.meikiem.foursquarevenuesnearby.data.cache.db

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import me.meikiem.foursquarevenuesnearby.data.cache.db.database.AppDatabase
import me.meikiem.foursquarevenuesnearby.data.cache.db.mapper.VenueCachedMapper
import me.meikiem.foursquarevenuesnearby.data.cache.db.mapper.VenuePagedCacheMapper
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.CacheInfo
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.CACHE_EXPIRATION_TIME_MILLISECONDS
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueEntity
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesCache
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

class VenuesCacheImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: VenueCachedMapper,
    private val pagedMapper: VenuePagedCacheMapper
) : VenuesCache {

    override fun clearVenuesNearby(): Completable {

        return Completable.defer {
            appDatabase.venueCachedDao().deleteVenueRecommendations()
            Completable.complete()
        }
    }

    override fun saveVenuesNearby(nearPlace:String, venues: List<VenueEntity>): Completable {

        return Completable.defer {
            appDatabase.venueCachedDao()
                .replaceVenueRecommendations(venues.map { entity -> mapper.mapToCachedd(nearPlace, entity) })
            Completable.complete()
        }
    }

    override fun getVenuesNearby(nearPlace: String): Single<List<VenueEntity>> {

        return appDatabase.venueCachedDao().getVenueRecommendations(nearPlace).firstOrError()
            .map { it.map { cached -> mapper.mapFromCached(cached) } }


    }

    override fun areVenuesNearbyCached(placeName: String): Single<Boolean> {
        return Single.zip(
            appDatabase.cacheUpdateTimeDao().getCacheInfo().toSingle(
                CacheInfo(lastUpdateTime = 0, nearPlace = "")
            ).map { it.nearPlace.equals(placeName, ignoreCase = true) },
            appDatabase.venueCachedDao().getVenueRecommendations(placeName).isEmpty.map { !it },
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { isCorrectNearPlaceCached, areAnyVenuesCached ->
                Pair(isCorrectNearPlaceCached, areAnyVenuesCached)
            }
        ).flatMap {
            Single.just(it.first && it.second)
        }

    }

    override fun setLastCacheInfo(lastUpdateTime: Long, placeName: String): Completable {

        return Completable.defer {
            appDatabase.cacheUpdateTimeDao().replaceCacheInfo(
                CacheInfo(lastUpdateTime = lastUpdateTime, nearPlace = placeName)
            )
            Completable.complete()
        }
    }

    override fun isVenuesNearbyCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (CACHE_EXPIRATION_TIME_MILLISECONDS).toLong()
        return appDatabase.cacheUpdateTimeDao().getCacheInfo().toSingle(
            CacheInfo(lastUpdateTime = 0, nearPlace = "")
        ).map {
            currentTime - it.lastUpdateTime > expirationTime
        }
    }



    override fun getVenuesPagingNearBy(nearPlace: String): LiveData<PagedList<VenueObject>> {

        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .build()
        val result = appDatabase.venueCachedDao().getVenuePagingRecommendations(nearPlace).mapByPage { input -> input.let {
                    it.map { venueCached ->
                        pagedMapper.mapFromCached(venueCached)
                    }
                }
            }.toLiveData(config)
        return result
    }

}
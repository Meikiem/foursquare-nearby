package me.meikiem.foursquarevenuesnearby.data.cache.db

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import me.meikiem.foursquarevenuesnearby.data.cache.db.database.AppDatabase
import me.meikiem.foursquarevenuesnearby.data.cache.db.mapper.VenueDetailCachedMapper
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.CacheDetailInfo
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.CACHE_EXPIRATION_TIME_MILLISECONDS
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailEntity
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueDetailsCache
import javax.inject.Inject


/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

class VenueDetailsCachedImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: VenueDetailCachedMapper
) : VenueDetailsCache {
    override fun clearVenueDetail(venueId: String): Completable {
        return Completable.defer {
            appDatabase.venueDetailsCachedDao().deleteVenueDetails(venueId)
            Completable.complete()
        }
    }

    override fun saveVenueDetail(venue: VenueDetailEntity): Completable {

        return Completable.defer {
            appDatabase.venueDetailsCachedDao()
                .replaceVenueDetails(venue.let { venueDetailEntity -> mapper.mapToCached(venueDetailEntity) })
            Completable.complete()
        }
    }

    override fun getVenueDetails(venueId: String): Single<VenueDetailEntity> {
        return appDatabase.venueDetailsCachedDao().getVenueDetails(venueId).toSingle().map { it ->
            mapper.mapFromCached(it)
        }
    }

    override fun areVenueDetailCached(venueId: String): Single<Boolean> {
        return Single.zip(
            appDatabase.cacheDetailUpdateTimeDao().getCacheDetailsInfo(venueId).toSingle(
                CacheDetailInfo(lastUpdateTime = 0, venueId = "")
            ).map {
                it.venueId.equals(venueId, ignoreCase = true)
            },
            appDatabase.venueDetailsCachedDao().getVenueDetails(venueId).isEmpty.map { !it },
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { isCorrectNearPlaceCached, areAnyVenuesCached ->
                Pair(isCorrectNearPlaceCached, areAnyVenuesCached)
            }
        ).flatMap {
            Single.just(it.first && it.second)
        }
    }

    override fun setLastCacheInfo(lastUpdateTime: Long, venueId: String): Completable {
        return Completable.defer {
            appDatabase.cacheDetailUpdateTimeDao().replaceCacheDetailsInfo(
                CacheDetailInfo(lastUpdateTime = lastUpdateTime, venueId = venueId)
            )
            Completable.complete()
        }
    }

    override fun isVenueDetailCacheExpired(venueId: String): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (CACHE_EXPIRATION_TIME_MILLISECONDS).toLong()
        return appDatabase.cacheDetailUpdateTimeDao().getCacheDetailsInfo(venueId).toSingle(
            CacheDetailInfo(lastUpdateTime = 0, venueId = "")
        ).map {
            currentTime - it.lastUpdateTime > expirationTime
        }
    }


}
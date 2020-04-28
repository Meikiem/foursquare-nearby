package me.meikiem.foursquarevenuesnearby.data.cache.db.mapper

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

interface CacheMapper<CachedModel, DataModel> {

    fun mapFromCached(cached: CachedModel): DataModel

    fun mapToCached(entity: DataModel): CachedModel

}

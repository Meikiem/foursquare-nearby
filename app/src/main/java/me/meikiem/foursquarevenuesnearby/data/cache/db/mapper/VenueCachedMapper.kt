package me.meikiem.foursquarevenuesnearby.data.cache.db.mapper

import me.meikiem.foursquarevenuesnearby.data.cache.db.model.VenueCached
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueEntity
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

class VenueCachedMapper @Inject constructor() : CacheMapper<VenueCached, VenueEntity> {

    override fun mapFromCached(cached: VenueCached): VenueEntity {
        return VenueEntity(cached.id, cached.name, cached.latitude, cached.longitude)
    }

    override fun mapToCached(entity: VenueEntity): VenueCached {
        return VenueCached(entity.id, entity.name, entity.latitude, entity.longitude, "")
    }

    fun mapToCachedd(nearPlace:String, entity: VenueEntity):VenueCached{
        return VenueCached(entity.id, entity.name, entity.latitude, entity.longitude, nearPlace)
    }


}
package me.meikiem.foursquarevenuesnearby.data.cache.db.mapper

import me.meikiem.foursquarevenuesnearby.data.cache.db.model.VenueCached
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/22/20.
 */

class VenuePagedCacheMapper @Inject constructor() : CacheMapper<VenueCached, VenueObject> {

    override fun mapFromCached(cached: VenueCached): VenueObject {
        return VenueObject(cached.id, cached.name, cached.latitude, cached.longitude)
    }

    override fun mapToCached(entity: VenueObject): VenueCached {
        return VenueCached(entity.id, entity.name, entity.latitude, entity.longitude, "")
    }

    fun mapToCachedd(nearPlace:String, entity: VenueObject):VenueCached{
        return VenueCached(entity.id, entity.name, entity.latitude, entity.longitude, nearPlace)
    }


}
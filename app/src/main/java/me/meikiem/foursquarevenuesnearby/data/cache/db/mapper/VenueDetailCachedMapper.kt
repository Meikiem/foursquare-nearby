package me.meikiem.foursquarevenuesnearby.data.cache.db.mapper

import me.meikiem.foursquarevenuesnearby.data.cache.db.model.VenueDetailCached
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailEntity
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

class VenueDetailCachedMapper @Inject constructor() : CacheMapper<VenueDetailCached, VenueDetailEntity> {

    override fun mapFromCached(cached: VenueDetailCached): VenueDetailEntity {
        return VenueDetailEntity(cached.photo?:"",
            cached.phone?:"",
            cached.description?:"",
            cached.id,
            cached.likes?:"",
            cached.address?:"",
            cached.name?:"",
            cached.rating?:0.0)
    }

    override fun mapToCached(entity: VenueDetailEntity): VenueDetailCached {
        return VenueDetailCached(entity.id,
            entity.name,
            entity.photo,
            entity.phone,
            entity.description,
            entity.likes,
            entity.address,
            entity.rating)
    }

}
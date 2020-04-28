package me.meikiem.foursquarevenuesnearby.data.mapper

import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailEntity
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueDetailsObject
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

class VenueDetailsMapper @Inject constructor() : EntityMapper<VenueDetailEntity, VenueDetailsObject>{
    override fun mapFromEntity(entity: VenueDetailEntity): VenueDetailsObject {
        return VenueDetailsObject(
            entity.photo,
            entity.phone,
            entity.description,
            entity.id,
            entity.likes,
            entity.address,
            entity.name,
            entity.rating)
    }

    override fun mapToEntity(domain: VenueDetailsObject): VenueDetailEntity {
        return VenueDetailEntity(
            domain.photo,
            domain.phone,
            domain.description,
            domain.id,
            domain.likes,
            domain.address,
            domain.name,
            domain.rating
        )
    }

}
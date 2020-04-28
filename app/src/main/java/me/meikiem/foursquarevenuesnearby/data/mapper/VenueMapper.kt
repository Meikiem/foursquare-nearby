package me.meikiem.foursquarevenuesnearby.data.mapper

import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueEntity
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

open class VenueMapper @Inject constructor() : EntityMapper<VenueEntity, VenueObject> {

    override fun mapFromEntity(entity: VenueEntity): VenueObject {
        return VenueObject(entity.id, entity.name, entity.latitude, entity.longitude)
    }

    override fun mapToEntity(domain: VenueObject): VenueEntity {
        return VenueEntity(domain.id, domain.name, domain.latitude, domain.longitude)
    }

}
package me.meikiem.foursquarevenuesnearby.data.entity.model

import me.meikiem.foursquarevenuesnearby.data.entity.ApiResponseModelMapper
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.Item
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueEntity
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

open class VenuesNearByApiResponseModelMapper @Inject constructor() :
    ApiResponseModelMapper<Item, VenueEntity> {

    override fun mapFromApiResponseModel(remote: Item): VenueEntity {
        return VenueEntity(remote.venue.id,
            remote.venue.name,
            remote.venue.location.lat,
            remote.venue.location.lng)
    }

}
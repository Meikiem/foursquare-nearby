package me.meikiem.foursquarevenuesnearby.data.entity.model

import me.meikiem.foursquarevenuesnearby.data.entity.ApiResponseModelMapper
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailEntity
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueDetailItem
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */


open class VenueDetailApiResponseModelMapper @Inject constructor():
    ApiResponseModelMapper<VenueDetailItem, VenueDetailEntity> {
    override fun mapFromApiResponseModel(remote: VenueDetailItem): VenueDetailEntity {
        return VenueDetailEntity("${remote.bestPhoto.prefix}500x500${remote.bestPhoto.suffix}",
            remote.contact.formattedPhone?:"",
            remote.description?:"",
            remote.id?:"",
            remote.likes.summary?:"",
            remote.location.address?:"",
            remote.name?:"",
            remote.rating?:0.0)
    }

}
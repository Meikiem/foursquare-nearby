package me.meikiem.foursquarevenuesnearby.data.entity

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */
interface ApiResponseModelMapper<RemoteModel, DataEntity> {

    fun mapFromApiResponseModel(remote: RemoteModel): DataEntity
}
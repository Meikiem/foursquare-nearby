package me.meikiem.foursquarevenuesnearby.data.mapper

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

interface EntityMapper<DataModel, DomainModel> {

    fun mapFromEntity(entity: DataModel): DomainModel

    fun mapToEntity(domain: DomainModel): DataModel

}
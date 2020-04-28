package me.meikiem.foursquarevenuesnearby.data.repository

import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.VenuesNearByApiResponseModelMapper
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueEntity
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenueRepository
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesRemote
import me.meikiem.foursquarevenuesnearby.data.util.GROUP_TYPE
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

class VenuesRemoteRepositoryImpl @Inject constructor(
    private val venues: VenuesRemote,
    private val responseModelMapper:VenuesNearByApiResponseModelMapper
): VenueRepository {
    override fun getVenuesNearby(placeName: String, limit:Int, offset:Int): Single<List<VenueEntity>> {

        val recommendedGroup = venues.getVenuesNearBy(placeName, limit, offset).map { remote ->
            remote.first { group -> group.name == GROUP_TYPE }
        }

        val venueEntities = recommendedGroup.map { group ->
            group.items.map { item -> responseModelMapper.mapFromApiResponseModel(item) }
        }

        return venueEntities
    }

}
package me.meikiem.foursquarevenuesnearby.data.store

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.reactivex.Completable
import io.reactivex.Single
import me.meikiem.foursquarevenuesnearby.data.entity.model.VenuesNearByApiResponseModelMapper
import me.meikiem.foursquarevenuesnearby.data.entity.model.remote.VenueEntity
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesDataStore
import me.meikiem.foursquarevenuesnearby.data.repository.venue.VenuesRemote
import me.meikiem.foursquarevenuesnearby.data.util.GROUP_TYPE
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

open class VenuesRemoteDataStore @Inject constructor(
    private val venuesRemote: VenuesRemote,
    private val responseModelMapper: VenuesNearByApiResponseModelMapper
) : VenuesDataStore {

    override fun getVenuesNearby(placeName: String, limit:Int, offset:Int): Single<List<VenueEntity>> {

        val recommendedGroup = venuesRemote.getVenuesNearBy(placeName, limit, offset).map {
                remote ->
            remote.first { group -> group.name == GROUP_TYPE }
        }

        val venueEntities = recommendedGroup.map { group ->
            group.items.map { item -> responseModelMapper.mapFromApiResponseModel(item) }
        }

        return venueEntities
    }

    override fun saveVenuesNearby(placeName: String, limit: Int, offset: Int, venues: List<VenueEntity>): Completable {
        throw UnsupportedOperationException("Saving venues isn't supported here...")
    }

    override fun clearVenuesNearby(): Completable {
        throw UnsupportedOperationException("Clearing venues isn't supported here...")
    }

    override fun getVenuesPagingNearby(nearPlace:String): LiveData<PagedList<VenueObject>> {
        throw UnsupportedOperationException("Clearing venues isn't supported here...")
    }

}
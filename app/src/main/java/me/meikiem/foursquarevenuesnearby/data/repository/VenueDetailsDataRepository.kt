package me.meikiem.foursquarevenuesnearby.data.repository

import me.meikiem.foursquarevenuesnearby.data.mapper.VenueDetailsMapper
import me.meikiem.foursquarevenuesnearby.data.store.VenueDetailsDataStoreFactory
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

class VenueDetailsDataRepository @Inject constructor(
    private val mapper:VenueDetailsMapper,
    private val venueDetailsDataStoreFactory: VenueDetailsDataStoreFactory
)
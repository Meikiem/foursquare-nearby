package me.meikiem.foursquarevenuesnearby.presentation.ui

import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */
interface MainNavigator {
    fun onVenueClicked(item: VenueObject)
    fun retry()
    fun onBack()
}
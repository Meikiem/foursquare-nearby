package me.meikiem.foursquarevenuesnearby.data.cache.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.VENUE_DETAIL_CACHED_ID
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.VENUE_DETAIL_CACHED_TABLE

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

@Entity(tableName = VENUE_DETAIL_CACHED_TABLE)
data class VenueDetailCached(

    @PrimaryKey
    @ColumnInfo(name = VENUE_DETAIL_CACHED_ID)
    var id: String,
    var name: String?,
    val photo: String?,
    val phone: String?,
    val description: String?,
    val likes: String?,
    val address: String?,
    val rating: Double?

)

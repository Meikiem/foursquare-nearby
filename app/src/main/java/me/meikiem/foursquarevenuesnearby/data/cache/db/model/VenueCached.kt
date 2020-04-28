package me.meikiem.foursquarevenuesnearby.data.cache.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.VENUE_CACHED_ID
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.VENUE_CACHED_TABLE

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Entity(tableName = VENUE_CACHED_TABLE)
data class VenueCached(

    @PrimaryKey
    @ColumnInfo(name = VENUE_CACHED_ID)
    var id: String,
    var name: String,
    var latitude: Double,
    var longitude: Double,
    var nearPlace:String

)
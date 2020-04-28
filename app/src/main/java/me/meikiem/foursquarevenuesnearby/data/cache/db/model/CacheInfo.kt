package me.meikiem.foursquarevenuesnearby.data.cache.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.CACHE_INFO_TABLE

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Entity(tableName = CACHE_INFO_TABLE)
data class CacheInfo(

    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    var lastUpdateTime: Long,
    var nearPlace: String
)
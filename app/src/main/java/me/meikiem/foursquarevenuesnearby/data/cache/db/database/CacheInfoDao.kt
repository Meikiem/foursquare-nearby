package me.meikiem.foursquarevenuesnearby.data.cache.db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.CacheInfo
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.CACHE_INFO_TABLE

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Dao
abstract class CacheInfoDao {

    @Query("SELECT * FROM $CACHE_INFO_TABLE")
    abstract fun getCacheInfo(): Maybe<CacheInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun replaceCacheInfo(cacheInfo: CacheInfo)

}
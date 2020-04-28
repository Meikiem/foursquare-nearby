package me.meikiem.foursquarevenuesnearby.data.cache.db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.CacheDetailInfo
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.CACHE_DETAIL_INFO_TABLE

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/22/20.
 */

@Dao
abstract class CacheDetailInfoDao {

    @Query("SELECT * FROM $CACHE_DETAIL_INFO_TABLE WHERE venueId=:venueId")
    abstract fun getCacheDetailsInfo(venueId:String): Maybe<CacheDetailInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun replaceCacheDetailsInfo(cacheInfo: CacheDetailInfo)

}
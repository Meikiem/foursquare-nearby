package me.meikiem.foursquarevenuesnearby.data.cache.db.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.VenueCached
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.VENUE_CACHED_TABLE

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Dao
abstract class VenueCachedDao {

    @Query("SELECT * FROM $VENUE_CACHED_TABLE WHERE nearPlace=:nearPlace")
    abstract fun getVenuePagingRecommendations(nearPlace:String): DataSource.Factory<Int, VenueCached>

    @Query("SELECT * FROM $VENUE_CACHED_TABLE WHERE nearPlace=:nearPlace")
    abstract fun getVenueRecommendations(nearPlace:String): Flowable<List<VenueCached>>

    @Query("DELETE FROM $VENUE_CACHED_TABLE")
    abstract fun deleteVenueRecommendations()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun replaceVenueRecommendations(venues: List<VenueCached>)

}
package me.meikiem.foursquarevenuesnearby.data.cache.db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.VenueDetailCached
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.VENUE_DETAIL_CACHED_ID
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.VENUE_DETAIL_CACHED_TABLE

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

@Dao
abstract class VenueDetailCachedDao {

    @Query("SELECT * FROM $VENUE_DETAIL_CACHED_TABLE WHERE $VENUE_DETAIL_CACHED_ID = :venueId")
    abstract fun getVenueDetails(venueId:String): Maybe<VenueDetailCached>

    @Query("DELETE FROM $VENUE_DETAIL_CACHED_TABLE WHERE $VENUE_DETAIL_CACHED_ID = :venueId")
    abstract fun deleteVenueDetails(venueId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun replaceVenueDetails(venues:VenueDetailCached)

}
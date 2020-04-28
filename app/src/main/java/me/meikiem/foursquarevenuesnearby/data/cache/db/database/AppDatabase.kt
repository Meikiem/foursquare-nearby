package me.meikiem.foursquarevenuesnearby.data.cache.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.CacheDetailInfo
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.CacheInfo
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.VenueCached
import me.meikiem.foursquarevenuesnearby.data.cache.db.model.VenueDetailCached
import me.meikiem.foursquarevenuesnearby.data.cache.db.util.APP_DATABASE

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Database(entities = [VenueCached::class, VenueDetailCached::class, CacheInfo::class, CacheDetailInfo::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun venueCachedDao(): VenueCachedDao

    abstract fun cacheUpdateTimeDao(): CacheInfoDao

    abstract fun venueDetailsCachedDao() : VenueDetailCachedDao

    abstract fun cacheDetailUpdateTimeDao() : CacheDetailInfoDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE).build()
        }

        fun destroyInstance() {
            instance = null
        }

    }


}
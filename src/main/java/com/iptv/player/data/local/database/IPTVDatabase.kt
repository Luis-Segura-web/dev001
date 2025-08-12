package com.iptv.player.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.iptv.player.data.local.dao.*
import com.iptv.player.data.local.entities.*

@Database(
    entities = [
        ChannelEntity::class,
        MovieEntity::class,
        TVSeriesEntity::class,
        SeasonEntity::class,
        EpisodeEntity::class,
        CategoryEntity::class,
        FavoriteEntity::class,
        WatchHistoryEntity::class,
        EPGProgramEntity::class,
        UserSettingsEntity::class,
        UserCredentialsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ContentTypeConverter::class, StringListConverter::class)
abstract class IPTVDatabase : RoomDatabase() {

    abstract fun channelDao(): ChannelDao
    abstract fun movieDao(): MovieDao
    abstract fun tvSeriesDao(): TVSeriesDao
    abstract fun seasonDao(): SeasonDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun categoryDao(): CategoryDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun watchHistoryDao(): WatchHistoryDao
    abstract fun epgDao(): EPGDao
    abstract fun userSettingsDao(): UserSettingsDao
    abstract fun userCredentialsDao(): UserCredentialsDao

    companion object {
        @Volatile
        private var INSTANCE: IPTVDatabase? = null

        fun getDatabase(context: Context): IPTVDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IPTVDatabase::class.java,
                    "iptv_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

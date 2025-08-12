package com.iptv.player.di

import android.content.Context
import androidx.room.Room
import com.iptv.player.data.local.dao.*
import com.iptv.player.data.local.database.IPTVDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideIPTVDatabase(@ApplicationContext context: Context): IPTVDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            IPTVDatabase::class.java,
            "iptv_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideChannelDao(database: IPTVDatabase): ChannelDao {
        return database.channelDao()
    }

    @Provides
    fun provideMovieDao(database: IPTVDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    fun provideTVSeriesDao(database: IPTVDatabase): TVSeriesDao {
        return database.tvSeriesDao()
    }

    @Provides
    fun provideSeasonDao(database: IPTVDatabase): SeasonDao {
        return database.seasonDao()
    }

    @Provides
    fun provideEpisodeDao(database: IPTVDatabase): EpisodeDao {
        return database.episodeDao()
    }

    @Provides
    fun provideCategoryDao(database: IPTVDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun provideFavoriteDao(database: IPTVDatabase): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    fun provideWatchHistoryDao(database: IPTVDatabase): WatchHistoryDao {
        return database.watchHistoryDao()
    }

    @Provides
    fun provideEPGDao(database: IPTVDatabase): EPGDao {
        return database.epgDao()
    }

    @Provides
    fun provideUserSettingsDao(database: IPTVDatabase): UserSettingsDao {
        return database.userSettingsDao()
    }

    @Provides
    fun provideUserCredentialsDao(database: IPTVDatabase): UserCredentialsDao {
        return database.userCredentialsDao()
    }
}

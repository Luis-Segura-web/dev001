package com.iptv.player.data.local.dao

import androidx.room.*
import com.iptv.player.data.local.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelDao {
    @Query("SELECT * FROM channels ORDER BY name ASC")
    fun getAllChannels(): Flow<List<ChannelEntity>>

    @Query("SELECT * FROM channels WHERE categoryId = :categoryId ORDER BY name ASC")
    fun getChannelsByCategory(categoryId: String): Flow<List<ChannelEntity>>

    @Query("SELECT * FROM channels WHERE isFavorite = 1 ORDER BY name ASC")
    fun getFavoriteChannels(): Flow<List<ChannelEntity>>

    @Query("SELECT * FROM channels WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchChannels(query: String): Flow<List<ChannelEntity>>

    @Query("SELECT * FROM channels WHERE id = :id")
    suspend fun getChannelById(id: Int): ChannelEntity?

    @Query("SELECT * FROM channels ORDER BY lastWatched DESC LIMIT :limit")
    fun getRecentChannels(limit: Int = 10): Flow<List<ChannelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannels(channels: List<ChannelEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannel(channel: ChannelEntity)

    @Update
    suspend fun updateChannel(channel: ChannelEntity)

    @Query("UPDATE channels SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("UPDATE channels SET lastWatched = :timestamp WHERE id = :id")
    suspend fun updateLastWatched(id: Int, timestamp: Long)

    @Delete
    suspend fun deleteChannel(channel: ChannelEntity)

    @Query("DELETE FROM channels")
    suspend fun deleteAllChannels()
}

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY name ASC")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE categoryId = :categoryId ORDER BY name ASC")
    fun getMoviesByCategory(categoryId: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE isFavorite = 1 ORDER BY name ASC")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchMovies(query: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Query("SELECT * FROM movies ORDER BY lastWatched DESC LIMIT :limit")
    fun getRecentMovies(limit: Int = 10): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE rating >= 7.0 ORDER BY rating DESC LIMIT :limit")
    fun getFeaturedMovies(limit: Int = 20): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Query("UPDATE movies SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("UPDATE movies SET lastWatched = :timestamp WHERE id = :id")
    suspend fun updateLastWatched(id: Int, timestamp: Long)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()
}

@Dao
interface TVSeriesDao {
    @Query("SELECT * FROM tv_series ORDER BY name ASC")
    fun getAllSeries(): Flow<List<TVSeriesEntity>>

    @Query("SELECT * FROM tv_series WHERE categoryId = :categoryId ORDER BY name ASC")
    fun getSeriesByCategory(categoryId: String): Flow<List<TVSeriesEntity>>

    @Query("SELECT * FROM tv_series WHERE isFavorite = 1 ORDER BY name ASC")
    fun getFavoriteSeries(): Flow<List<TVSeriesEntity>>

    @Query("SELECT * FROM tv_series WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchSeries(query: String): Flow<List<TVSeriesEntity>>

    @Query("SELECT * FROM tv_series WHERE id = :id")
    suspend fun getSeriesById(id: Int): TVSeriesEntity?

    @Query("SELECT * FROM tv_series ORDER BY lastWatched DESC LIMIT :limit")
    fun getRecentSeries(limit: Int = 10): Flow<List<TVSeriesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeries(series: List<TVSeriesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingleSeries(series: TVSeriesEntity)

    @Update
    suspend fun updateSeries(series: TVSeriesEntity)

    @Query("UPDATE tv_series SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("UPDATE tv_series SET lastWatched = :timestamp WHERE id = :id")
    suspend fun updateLastWatched(id: Int, timestamp: Long)

    @Delete
    suspend fun deleteSeries(series: TVSeriesEntity)

    @Query("DELETE FROM tv_series")
    suspend fun deleteAllSeries()
}

@Dao
interface SeasonDao {
    @Query("SELECT * FROM seasons WHERE seriesId = :seriesId ORDER BY seasonNumber ASC")
    fun getSeasonsBySeriesId(seriesId: Int): Flow<List<SeasonEntity>>

    @Query("SELECT * FROM seasons WHERE id = :id")
    suspend fun getSeasonById(id: Int): SeasonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeasons(seasons: List<SeasonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeason(season: SeasonEntity)

    @Update
    suspend fun updateSeason(season: SeasonEntity)

    @Delete
    suspend fun deleteSeason(season: SeasonEntity)

    @Query("DELETE FROM seasons WHERE seriesId = :seriesId")
    suspend fun deleteSeasonsBySeriesId(seriesId: Int)
}

@Dao
interface EpisodeDao {
    @Query("SELECT * FROM episodes WHERE seriesId = :seriesId AND seasonNumber = :seasonNumber ORDER BY episodeNumber ASC")
    fun getEpisodesBySeasonId(seriesId: Int, seasonNumber: Int): Flow<List<EpisodeEntity>>

    @Query("SELECT * FROM episodes WHERE seriesId = :seriesId ORDER BY seasonNumber ASC, episodeNumber ASC")
    fun getEpisodesBySeriesId(seriesId: Int): Flow<List<EpisodeEntity>>

    @Query("SELECT * FROM episodes WHERE id = :id")
    suspend fun getEpisodeById(id: String): EpisodeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: EpisodeEntity)

    @Update
    suspend fun updateEpisode(episode: EpisodeEntity)

    @Delete
    suspend fun deleteEpisode(episode: EpisodeEntity)

    @Query("DELETE FROM episodes WHERE seriesId = :seriesId")
    suspend fun deleteEpisodesBySeriesId(seriesId: Int)
}

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories WHERE type = :type ORDER BY name ASC")
    fun getCategoriesByType(type: String): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategoryById(id: String): CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Query("DELETE FROM categories WHERE type = :type")
    suspend fun deleteCategoriesByType(type: String)
}

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY addedAt DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE contentType = :contentType ORDER BY addedAt DESC")
    fun getFavoritesByType(contentType: String): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorites WHERE contentId = :contentId AND contentType = :contentType")
    suspend fun getFavorite(contentId: String, contentType: String): FavoriteEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE contentId = :contentId AND contentType = :contentType)")
    suspend fun isFavorite(contentId: String, contentType: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE contentId = :contentId AND contentType = :contentType")
    suspend fun deleteFavoriteByContent(contentId: String, contentType: String)

    @Query("DELETE FROM favorites")
    suspend fun deleteAllFavorites()
}

@Dao
interface WatchHistoryDao {
    @Query("SELECT * FROM watch_history ORDER BY lastWatchedAt DESC LIMIT :limit")
    fun getWatchHistory(limit: Int = 50): Flow<List<WatchHistoryEntity>>

    @Query("SELECT * FROM watch_history WHERE contentType = :contentType ORDER BY lastWatchedAt DESC")
    fun getWatchHistoryByType(contentType: String): Flow<List<WatchHistoryEntity>>

    @Query("SELECT * FROM watch_history WHERE contentId = :contentId")
    suspend fun getWatchHistoryItem(contentId: String): WatchHistoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchHistoryItem(item: WatchHistoryEntity)

    @Update
    suspend fun updateWatchHistoryItem(item: WatchHistoryEntity)

    @Delete
    suspend fun deleteWatchHistoryItem(item: WatchHistoryEntity)

    @Query("DELETE FROM watch_history WHERE contentId = :contentId")
    suspend fun deleteWatchHistoryByContentId(contentId: String)

    @Query("DELETE FROM watch_history")
    suspend fun deleteAllWatchHistory()

    @Query("DELETE FROM watch_history WHERE lastWatchedAt < :timestamp")
    suspend fun deleteOldWatchHistory(timestamp: Long)
}

@Dao
interface EPGDao {
    @Query("SELECT * FROM epg_programs WHERE channelId = :channelId AND startTime >= :startTime AND endTime <= :endTime ORDER BY startTime ASC")
    fun getProgramsByChannelAndTimeRange(channelId: String, startTime: Long, endTime: Long): Flow<List<EPGProgramEntity>>

    @Query("SELECT * FROM epg_programs WHERE channelId = :channelId ORDER BY startTime ASC")
    fun getProgramsByChannel(channelId: String): Flow<List<EPGProgramEntity>>

    @Query("SELECT * FROM epg_programs WHERE startTime <= :currentTime AND endTime >= :currentTime")
    fun getCurrentPrograms(currentTime: Long): Flow<List<EPGProgramEntity>>

    @Query("SELECT * FROM epg_programs WHERE id = :id")
    suspend fun getProgramById(id: String): EPGProgramEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrograms(programs: List<EPGProgramEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgram(program: EPGProgramEntity)

    @Update
    suspend fun updateProgram(program: EPGProgramEntity)

    @Delete
    suspend fun deleteProgram(program: EPGProgramEntity)

    @Query("DELETE FROM epg_programs WHERE channelId = :channelId")
    suspend fun deleteProgramsByChannel(channelId: String)

    @Query("DELETE FROM epg_programs WHERE endTime < :timestamp")
    suspend fun deleteOldPrograms(timestamp: Long)

    @Query("DELETE FROM epg_programs")
    suspend fun deleteAllPrograms()
}

@Dao
interface UserSettingsDao {
    @Query("SELECT * FROM user_settings WHERE id = 1")
    suspend fun getUserSettings(): UserSettingsEntity?

    @Query("SELECT * FROM user_settings WHERE id = 1")
    fun getUserSettingsFlow(): Flow<UserSettingsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSettings(settings: UserSettingsEntity)

    @Update
    suspend fun updateUserSettings(settings: UserSettingsEntity)
}

@Dao
interface UserCredentialsDao {
    @Query("SELECT * FROM user_credentials WHERE id = 1")
    suspend fun getUserCredentials(): UserCredentialsEntity?

    @Query("SELECT * FROM user_credentials WHERE id = 1")
    fun getUserCredentialsFlow(): Flow<UserCredentialsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCredentials(credentials: UserCredentialsEntity)

    @Update
    suspend fun updateUserCredentials(credentials: UserCredentialsEntity)

    @Query("DELETE FROM user_credentials")
    suspend fun deleteUserCredentials()
}

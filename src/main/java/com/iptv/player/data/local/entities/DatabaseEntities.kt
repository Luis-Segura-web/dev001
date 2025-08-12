package com.iptv.player.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iptv.player.domain.models.ContentType

@Entity(tableName = "channels")
data class ChannelEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val streamUrl: String,
    val iconUrl: String?,
    val categoryId: String,
    val categoryName: String,
    val epgChannelId: String?,
    val hasArchive: Boolean = false,
    val archiveDuration: Int = 0,
    val isFavorite: Boolean = false,
    val lastWatched: Long = 0L
)

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val streamUrl: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val categoryId: String,
    val categoryName: String,
    val rating: Double = 0.0,
    val year: String?,
    val duration: String?,
    val description: String?,
    val genre: String?,
    val director: String?,
    val cast: String?,
    val tmdbId: String?,
    val trailerUrl: String?,
    val isFavorite: Boolean = false,
    val lastWatched: Long = 0L
)

@Entity(tableName = "tv_series")
data class TVSeriesEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val posterUrl: String?,
    val backdropUrl: String?,
    val categoryId: String,
    val categoryName: String,
    val rating: Double = 0.0,
    val year: String?,
    val description: String?,
    val genre: String?,
    val director: String?,
    val cast: String?,
    val tmdbId: String?,
    val trailerUrl: String?,
    val totalSeasons: Int = 0,
    val totalEpisodes: Int = 0,
    val isFavorite: Boolean = false,
    val lastWatched: Long = 0L
)

@Entity(tableName = "seasons")
data class SeasonEntity(
    @PrimaryKey val id: Int,
    val seriesId: Int,
    val seasonNumber: Int,
    val name: String,
    val overview: String?,
    val posterUrl: String?,
    val episodeCount: Int,
    val airDate: String?
)

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val id: String,
    val seriesId: Int,
    val seasonNumber: Int,
    val episodeNumber: Int,
    val title: String,
    val streamUrl: String,
    val overview: String?,
    val stillUrl: String?,
    val duration: String?,
    val airDate: String?,
    val rating: Double = 0.0,
    val tmdbId: String?
)

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val parentId: Int = 0,
    val type: String // "live", "vod", "series"
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val contentId: String,
    val contentType: String, // "channel", "movie", "series"
    val title: String,
    val posterUrl: String?,
    val addedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "watch_history")
@TypeConverters(ContentTypeConverter::class)
data class WatchHistoryEntity(
    @PrimaryKey val id: String,
    val contentId: String,
    val contentType: ContentType,
    val title: String,
    val posterUrl: String?,
    val lastWatchedPosition: Long,
    val duration: Long,
    val lastWatchedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "epg_programs")
data class EPGProgramEntity(
    @PrimaryKey val id: String,
    val channelId: String,
    val title: String,
    val description: String?,
    val startTime: Long,
    val endTime: Long,
    val language: String?
)

@Entity(tableName = "user_settings")
data class UserSettingsEntity(
    @PrimaryKey val id: Int = 1,
    val theme: String = "SYSTEM",
    val playbackQuality: String = "Auto",
    val autoPlay: Boolean = true,
    val showSubtitles: Boolean = false,
    val subtitleSize: String = "MEDIUM",
    val parentalControlEnabled: Boolean = false,
    val parentalControlPin: String? = null,
    val blockedCategories: String = "[]", // JSON array
    val maxRating: String? = null,
    val tmdbApiKey: String? = null
)

@Entity(tableName = "user_credentials")
data class UserCredentialsEntity(
    @PrimaryKey val id: Int = 1,
    val serverUrl: String,
    val username: String,
    val password: String,
    val port: String = "80",
    val lastLogin: Long = System.currentTimeMillis()
)

// Type Converters
class ContentTypeConverter {
    @TypeConverter
    fun fromContentType(contentType: ContentType): String {
        return when (contentType) {
            is ContentType.LiveTV -> "LiveTV"
            is ContentType.Movie -> "Movie"
            is ContentType.Series -> "Series"
        }
    }

    @TypeConverter
    fun toContentType(contentType: String): ContentType {
        return when (contentType) {
            "LiveTV" -> ContentType.LiveTV
            "Movie" -> ContentType.Movie
            "Series" -> ContentType.Series
            else -> ContentType.LiveTV
        }
    }
}

class StringListConverter {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType) ?: emptyList()
    }
}

// Relations for complex queries
data class ChannelWithPrograms(
    val channel: ChannelEntity,
    val programs: List<EPGProgramEntity>
)

data class SeriesWithSeasonsAndEpisodes(
    val series: TVSeriesEntity,
    val seasons: List<SeasonEntity>,
    val episodes: List<EpisodeEntity>
)

data class SeasonWithEpisodes(
    val season: SeasonEntity,
    val episodes: List<EpisodeEntity>
)

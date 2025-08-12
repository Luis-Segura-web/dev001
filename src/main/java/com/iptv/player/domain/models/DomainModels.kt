package com.iptv.player.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Channel(
    val id: Int,
    val name: String,
    val streamUrl: String,
    val iconUrl: String?,
    val categoryId: String,
    val categoryName: String,
    val epgChannelId: String?,
    val hasArchive: Boolean = false,
    val archiveDuration: Int = 0,
    val isFavorite: Boolean = false
) : Parcelable

@Parcelize
data class Movie(
    val id: Int,
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
    val isFavorite: Boolean = false
) : Parcelable

@Parcelize
data class TVSeries(
    val id: Int,
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
    val isFavorite: Boolean = false
) : Parcelable

@Parcelize
data class Season(
    val id: Int,
    val seriesId: Int,
    val seasonNumber: Int,
    val name: String,
    val overview: String?,
    val posterUrl: String?,
    val episodeCount: Int,
    val airDate: String?
) : Parcelable

@Parcelize
data class Episode(
    val id: String,
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
) : Parcelable

@Parcelize
data class Category(
    val id: String,
    val name: String,
    val parentId: Int = 0
) : Parcelable

@Parcelize
data class EPGProgram(
    val id: String,
    val channelId: String,
    val title: String,
    val description: String?,
    val startTime: Long,
    val endTime: Long,
    val language: String?
) : Parcelable

data class UserCredentials(
    val serverUrl: String,
    val username: String,
    val password: String,
    val port: String = "80"
)

data class PlayerState(
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val isBuffering: Boolean = false,
    val playbackSpeed: Float = 1.0f,
    val volume: Float = 1.0f,
    val isMuted: Boolean = false
)

data class SubtitleTrack(
    val id: String,
    val language: String,
    val label: String,
    val isSelected: Boolean = false
)

data class AudioTrack(
    val id: String,
    val language: String,
    val label: String,
    val isSelected: Boolean = false
)

sealed class ContentType {
    object LiveTV : ContentType()
    object Movie : ContentType()
    object Series : ContentType()
}

sealed class PlaybackQuality(val label: String, val value: String) {
    object Auto : PlaybackQuality("Auto", "auto")
    object Low : PlaybackQuality("480p", "480")
    object Medium : PlaybackQuality("720p", "720")
    object High : PlaybackQuality("1080p", "1080")
    object UltraHigh : PlaybackQuality("4K", "2160")
}

data class WatchHistoryItem(
    val contentId: String,
    val contentType: ContentType,
    val title: String,
    val posterUrl: String?,
    val lastWatchedPosition: Long,
    val duration: Long,
    val lastWatchedAt: Long,
    val progress: Float = if (duration > 0) lastWatchedPosition.toFloat() / duration else 0f
)

data class FavoriteItem(
    val contentId: String,
    val contentType: ContentType,
    val title: String,
    val posterUrl: String?,
    val addedAt: Long
)

data class ParentalControl(
    val isEnabled: Boolean = false,
    val pin: String? = null,
    val blockedCategories: List<String> = emptyList(),
    val maxRating: String? = null
)

data class AppSettings(
    val theme: AppTheme = AppTheme.SYSTEM,
    val playbackQuality: PlaybackQuality = PlaybackQuality.Auto,
    val autoPlay: Boolean = true,
    val showSubtitles: Boolean = false,
    val subtitleSize: SubtitleSize = SubtitleSize.MEDIUM,
    val parentalControl: ParentalControl = ParentalControl(),
    val tmdbApiKey: String? = null
)

enum class AppTheme {
    LIGHT, DARK, SYSTEM
}

enum class SubtitleSize(val scale: Float) {
    SMALL(0.8f),
    MEDIUM(1.0f),
    LARGE(1.2f),
    EXTRA_LARGE(1.4f)
}

// UI State Models
data class HomeUiState(
    val isLoading: Boolean = false,
    val featuredContent: List<Movie> = emptyList(),
    val recentChannels: List<Channel> = emptyList(),
    val recentMovies: List<Movie> = emptyList(),
    val recentSeries: List<TVSeries> = emptyList(),
    val watchHistory: List<WatchHistoryItem> = emptyList(),
    val error: String? = null
)

data class ChannelsUiState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val channels: List<Channel> = emptyList(),
    val selectedCategory: Category? = null,
    val searchQuery: String = "",
    val error: String? = null
)

data class MoviesUiState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val movies: List<Movie> = emptyList(),
    val selectedCategory: Category? = null,
    val searchQuery: String = "",
    val error: String? = null
)

data class SeriesUiState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val series: List<TVSeries> = emptyList(),
    val selectedCategory: Category? = null,
    val searchQuery: String = "",
    val error: String? = null
)

data class PlayerUiState(
    val isLoading: Boolean = false,
    val playerState: PlayerState = PlayerState(),
    val showControls: Boolean = true,
    val subtitleTracks: List<SubtitleTrack> = emptyList(),
    val audioTracks: List<AudioTrack> = emptyList(),
    val currentContent: Any? = null, // Channel, Movie, or Episode
    val error: String? = null
)

data class EPGUiState(
    val isLoading: Boolean = false,
    val programs: List<EPGProgram> = emptyList(),
    val selectedDate: Long = System.currentTimeMillis(),
    val selectedChannel: Channel? = null,
    val error: String? = null
)

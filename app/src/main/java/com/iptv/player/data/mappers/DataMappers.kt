package com.iptv.player.data.mappers

import com.iptv.player.data.local.entities.*
import com.iptv.player.data.models.*
import com.iptv.player.data.remote.api.TMDBApi
import com.iptv.player.data.remote.api.XtreamApi
import com.iptv.player.domain.models.*
import com.iptv.player.domain.models.Category
import com.iptv.player.domain.models.Channel
import com.iptv.player.domain.models.Episode
import com.iptv.player.domain.models.Movie
import com.iptv.player.domain.models.Season
import com.iptv.player.domain.models.TVSeries

// Xtream to Domain mappers
fun LiveStream.toDomain(
    serverUrl: String,
    username: String,
    password: String,
    categoryName: String = ""
): Channel {
    val streamUrl = XtreamApi.buildStreamUrl(
        serverUrl = serverUrl,
        username = username,
        password = password,
        streamId = streamId,
        type = "live"
    )
    
    return Channel(
        id = streamId,
        name = name,
        streamUrl = streamUrl,
        iconUrl = streamIcon.takeIf { it.isNotEmpty() },
        categoryId = categoryId,
        categoryName = categoryName,
        epgChannelId = epgChannelId.takeIf { it.isNotEmpty() },
        hasArchive = tvArchive == 1,
        archiveDuration = tvArchiveDuration
    )
}

fun VodStream.toDomain(
    serverUrl: String,
    username: String,
    password: String,
    categoryName: String = ""
): Movie {
    val streamUrl = XtreamApi.buildStreamUrl(
        serverUrl = serverUrl,
        username = username,
        password = password,
        streamId = streamId,
        type = "movie",
        extension = containerExtension
    )
    
    return Movie(
        id = streamId,
        name = name,
        streamUrl = streamUrl,
        posterUrl = streamIcon.takeIf { it.isNotEmpty() },
        backdropUrl = null,
        categoryId = categoryId,
        categoryName = categoryName,
        rating = rating5Based,
        year = null,
        duration = null,
        description = null,
        genre = null,
        director = null,
        cast = null,
        tmdbId = null,
        trailerUrl = null
    )
}

fun VodInfo.toDomain(
    serverUrl: String,
    username: String,
    password: String,
    categoryName: String = ""
): Movie {
    val streamUrl = XtreamApi.buildStreamUrl(
        serverUrl = serverUrl,
        username = username,
        password = password,
        streamId = movieData.streamId,
        type = "movie",
        extension = movieData.containerExtension
    )
    
    return Movie(
        id = movieData.streamId,
        name = info.name,
        streamUrl = streamUrl,
        posterUrl = info.movieImage.takeIf { it.isNotEmpty() },
        backdropUrl = info.coverBig.takeIf { it.isNotEmpty() },
        categoryId = movieData.categoryId,
        categoryName = categoryName,
        rating = info.rating5Based,
        year = info.releaseDate,
        duration = info.duration,
        description = info.plot.takeIf { it.isNotEmpty() },
        genre = info.genre.takeIf { it.isNotEmpty() },
        director = info.director.takeIf { it.isNotEmpty() },
        cast = info.cast.takeIf { it.isNotEmpty() },
        tmdbId = info.tmdbId.takeIf { it.isNotEmpty() },
        trailerUrl = info.youtubeTrailer.takeIf { it.isNotEmpty() }
    )
}

fun Series.toDomain(categoryName: String = ""): TVSeries {
    return TVSeries(
        id = seriesId,
        name = name,
        posterUrl = cover.takeIf { it.isNotEmpty() },
        backdropUrl = backdropPath.firstOrNull(),
        categoryId = categoryId,
        categoryName = categoryName,
        rating = rating5Based,
        year = releaseDate,
        description = plot.takeIf { it.isNotEmpty() },
        genre = genre.takeIf { it.isNotEmpty() },
        director = director.takeIf { it.isNotEmpty() },
        cast = cast.takeIf { it.isNotEmpty() },
        tmdbId = null,
        trailerUrl = youtubeTrailer.takeIf { it.isNotEmpty() }
    )
}

fun SeriesInfo.toDomain(categoryName: String = ""): TVSeries {
    return TVSeries(
        id = 0, // Will be set from the series list
        name = info.name,
        posterUrl = info.cover.takeIf { it.isNotEmpty() },
        backdropUrl = info.backdropPath.firstOrNull(),
        categoryId = info.categoryId,
        categoryName = categoryName,
        rating = info.rating5Based,
        year = info.releaseDate,
        description = info.plot.takeIf { it.isNotEmpty() },
        genre = info.genre.takeIf { it.isNotEmpty() },
        director = info.director.takeIf { it.isNotEmpty() },
        cast = info.cast.takeIf { it.isNotEmpty() },
        tmdbId = info.tmdbId.takeIf { it.isNotEmpty() },
        trailerUrl = info.youtubeTrailer.takeIf { it.isNotEmpty() },
        totalSeasons = seasons.size,
        totalEpisodes = episodes.values.sumOf { it.size }
    )
}

fun com.iptv.player.data.models.Season.toDomain(seriesId: Int): Season {
    return Season(
        id = id,
        seriesId = seriesId,
        seasonNumber = seasonNumber,
        name = name,
        overview = overview.takeIf { it.isNotEmpty() },
        posterUrl = posterPath.takeIf { it.isNotEmpty() },
        episodeCount = episodeCount,
        airDate = airDate
    )
}

fun com.iptv.player.data.models.Episode.toDomain(
    serverUrl: String,
    username: String,
    password: String,
    seriesId: Int
): Episode {
    val streamUrl = XtreamApi.buildStreamUrl(
        serverUrl = serverUrl,
        username = username,
        password = password,
        streamId = id.toInt(),
        type = "series",
        extension = containerExtension
    )
    
    return Episode(
        id = id,
        seriesId = seriesId,
        seasonNumber = season,
        episodeNumber = episodeNum,
        title = title,
        streamUrl = streamUrl,
        overview = info.plot.takeIf { it.isNotEmpty() },
        stillUrl = info.movieImage.takeIf { it.isNotEmpty() },
        duration = info.duration,
        airDate = info.releaseDate,
        rating = info.rating.toDoubleOrNull() ?: 0.0,
        tmdbId = info.tmdbId.takeIf { it.isNotEmpty() }
    )
}

fun com.iptv.player.data.models.Category.toDomain(): Category {
    return Category(
        id = categoryId,
        name = categoryName,
        parentId = parentId
    )
}

fun EpgProgram.toDomain(): EPGProgram {
    return EPGProgram(
        id = id,
        channelId = channelId,
        title = title,
        description = description.takeIf { it.isNotEmpty() },
        startTime = startTimestamp * 1000, // Convert to milliseconds
        endTime = stopTimestamp * 1000, // Convert to milliseconds
        language = lang.takeIf { it.isNotEmpty() }
    )
}

// Domain to Entity mappers
fun Channel.toEntity(): ChannelEntity {
    return ChannelEntity(
        id = id,
        name = name,
        streamUrl = streamUrl,
        iconUrl = iconUrl,
        categoryId = categoryId,
        categoryName = categoryName,
        epgChannelId = epgChannelId,
        hasArchive = hasArchive,
        archiveDuration = archiveDuration,
        isFavorite = isFavorite
    )
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        name = name,
        streamUrl = streamUrl,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        categoryId = categoryId,
        categoryName = categoryName,
        rating = rating,
        year = year,
        duration = duration,
        description = description,
        genre = genre,
        director = director,
        cast = cast,
        tmdbId = tmdbId,
        trailerUrl = trailerUrl,
        isFavorite = isFavorite
    )
}

fun TVSeries.toEntity(): TVSeriesEntity {
    return TVSeriesEntity(
        id = id,
        name = name,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        categoryId = categoryId,
        categoryName = categoryName,
        rating = rating,
        year = year,
        description = description,
        genre = genre,
        director = director,
        cast = cast,
        tmdbId = tmdbId,
        trailerUrl = trailerUrl,
        totalSeasons = totalSeasons,
        totalEpisodes = totalEpisodes,
        isFavorite = isFavorite
    )
}

fun Season.toEntity(): SeasonEntity {
    return SeasonEntity(
        id = id,
        seriesId = seriesId,
        seasonNumber = seasonNumber,
        name = name,
        overview = overview,
        posterUrl = posterUrl,
        episodeCount = episodeCount,
        airDate = airDate
    )
}

fun Episode.toEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id,
        seriesId = seriesId,
        seasonNumber = seasonNumber,
        episodeNumber = episodeNumber,
        title = title,
        streamUrl = streamUrl,
        overview = overview,
        stillUrl = stillUrl,
        duration = duration,
        airDate = airDate,
        rating = rating,
        tmdbId = tmdbId
    )
}

fun Category.toEntity(type: String): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        parentId = parentId,
        type = type
    )
}

fun EPGProgram.toEntity(): EPGProgramEntity {
    return EPGProgramEntity(
        id = id,
        channelId = channelId,
        title = title,
        description = description,
        startTime = startTime,
        endTime = endTime,
        language = language
    )
}

// Entity to Domain mappers
fun ChannelEntity.toDomain(): Channel {
    return Channel(
        id = id,
        name = name,
        streamUrl = streamUrl,
        iconUrl = iconUrl,
        categoryId = categoryId,
        categoryName = categoryName,
        epgChannelId = epgChannelId,
        hasArchive = hasArchive,
        archiveDuration = archiveDuration,
        isFavorite = isFavorite
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        name = name,
        streamUrl = streamUrl,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        categoryId = categoryId,
        categoryName = categoryName,
        rating = rating,
        year = year,
        duration = duration,
        description = description,
        genre = genre,
        director = director,
        cast = cast,
        tmdbId = tmdbId,
        trailerUrl = trailerUrl,
        isFavorite = isFavorite
    )
}

fun TVSeriesEntity.toDomain(): TVSeries {
    return TVSeries(
        id = id,
        name = name,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        categoryId = categoryId,
        categoryName = categoryName,
        rating = rating,
        year = year,
        description = description,
        genre = genre,
        director = director,
        cast = cast,
        tmdbId = tmdbId,
        trailerUrl = trailerUrl,
        totalSeasons = totalSeasons,
        totalEpisodes = totalEpisodes,
        isFavorite = isFavorite
    )
}

fun SeasonEntity.toDomain(): Season {
    return Season(
        id = id,
        seriesId = seriesId,
        seasonNumber = seasonNumber,
        name = name,
        overview = overview,
        posterUrl = posterUrl,
        episodeCount = episodeCount,
        airDate = airDate
    )
}

fun EpisodeEntity.toDomain(): Episode {
    return Episode(
        id = id,
        seriesId = seriesId,
        seasonNumber = seasonNumber,
        episodeNumber = episodeNumber,
        title = title,
        streamUrl = streamUrl,
        overview = overview,
        stillUrl = stillUrl,
        duration = duration,
        airDate = airDate,
        rating = rating,
        tmdbId = tmdbId
    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        parentId = parentId
    )
}

fun EPGProgramEntity.toDomain(): EPGProgram {
    return EPGProgram(
        id = id,
        channelId = channelId,
        title = title,
        description = description,
        startTime = startTime,
        endTime = endTime,
        language = language
    )
}

// TMDB Enhancement mappers
fun Movie.enhanceWithTMDB(tmdbMovie: TMDBMovieDetails): Movie {
    return copy(
        posterUrl = TMDBApi.buildPosterUrl(tmdbMovie.posterPath) ?: posterUrl,
        backdropUrl = TMDBApi.buildBackdropUrl(tmdbMovie.backdropPath) ?: backdropUrl,
        description = tmdbMovie.overview?.takeIf { it.isNotEmpty() } ?: description,
        genre = tmdbMovie.genres.joinToString(", ") { it.name }.takeIf { it.isNotEmpty() } ?: genre,
        rating = if (tmdbMovie.voteAverage > 0) tmdbMovie.voteAverage else rating,
        year = tmdbMovie.releaseDate?.take(4) ?: year,
        duration = tmdbMovie.runtime?.let { "${it} min" } ?: duration
    )
}

fun TVSeries.enhanceWithTMDB(tmdbTV: TMDBTVDetails): TVSeries {
    return copy(
        posterUrl = TMDBApi.buildPosterUrl(tmdbTV.posterPath) ?: posterUrl,
        backdropUrl = TMDBApi.buildBackdropUrl(tmdbTV.backdropPath) ?: backdropUrl,
        description = tmdbTV.overview?.takeIf { it.isNotEmpty() } ?: description,
        genre = tmdbTV.genres.joinToString(", ") { it.name }.takeIf { it.isNotEmpty() } ?: genre,
        rating = if (tmdbTV.voteAverage > 0) tmdbTV.voteAverage else rating,
        year = tmdbTV.firstAirDate?.take(4) ?: year,
        totalSeasons = tmdbTV.numberOfSeasons,
        totalEpisodes = tmdbTV.numberOfEpisodes
    )
}

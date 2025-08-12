package com.iptv.player.data.models

import com.google.gson.annotations.SerializedName

// Movie Details
data class TMDBMovieDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("genres") val genres: List<TMDBGenre>,
    @SerializedName("production_countries") val productionCountries: List<TMDBProductionCountry>,
    @SerializedName("spoken_languages") val spokenLanguages: List<TMDBSpokenLanguage>,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("status") val status: String,
    @SerializedName("budget") val budget: Long,
    @SerializedName("revenue") val revenue: Long
)

// TV Show Details
data class TMDBTVDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    @SerializedName("last_air_date") val lastAirDate: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("genres") val genres: List<TMDBGenre>,
    @SerializedName("production_countries") val productionCountries: List<TMDBProductionCountry>,
    @SerializedName("spoken_languages") val spokenLanguages: List<TMDBSpokenLanguage>,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int,
    @SerializedName("episode_run_time") val episodeRunTime: List<Int>,
    @SerializedName("seasons") val seasons: List<TMDBSeason>,
    @SerializedName("networks") val networks: List<TMDBNetwork>,
    @SerializedName("created_by") val createdBy: List<TMDBCreator>
)

// Common Models
data class TMDBGenre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class TMDBProductionCountry(
    @SerializedName("iso_3166_1") val iso31661: String,
    @SerializedName("name") val name: String
)

data class TMDBSpokenLanguage(
    @SerializedName("iso_639_1") val iso6391: String,
    @SerializedName("name") val name: String,
    @SerializedName("english_name") val englishName: String
)

data class TMDBSeason(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("season_number") val seasonNumber: Int,
    @SerializedName("episode_count") val episodeCount: Int,
    @SerializedName("air_date") val airDate: String?
)

data class TMDBNetwork(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("origin_country") val originCountry: String
)

data class TMDBCreator(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("credit_id") val creditId: String
)

// Credits
data class TMDBCredits(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<TMDBCastMember>,
    @SerializedName("crew") val crew: List<TMDBCrewMember>
)

data class TMDBCastMember(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("character") val character: String,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("order") val order: Int,
    @SerializedName("cast_id") val castId: Int,
    @SerializedName("credit_id") val creditId: String
)

data class TMDBCrewMember(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("job") val job: String,
    @SerializedName("department") val department: String,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("credit_id") val creditId: String
)

// Videos (Trailers)
data class TMDBVideosResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<TMDBVideo>
)

data class TMDBVideo(
    @SerializedName("id") val id: String,
    @SerializedName("key") val key: String,
    @SerializedName("name") val name: String,
    @SerializedName("site") val site: String,
    @SerializedName("type") val type: String,
    @SerializedName("official") val official: Boolean,
    @SerializedName("published_at") val publishedAt: String,
    @SerializedName("size") val size: Int
)

// Images
data class TMDBImagesResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrops") val backdrops: List<TMDBImage>,
    @SerializedName("posters") val posters: List<TMDBImage>,
    @SerializedName("logos") val logos: List<TMDBImage>
)

data class TMDBImage(
    @SerializedName("aspect_ratio") val aspectRatio: Double,
    @SerializedName("file_path") val filePath: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("iso_639_1") val iso6391: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)

// Search Results
data class TMDBSearchResponse<T>(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<T>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class TMDBMovieSearchResult(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("genre_ids") val genreIds: List<Int>
)

data class TMDBTVSearchResult(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originalLanguage: String
)

// Configuration
data class TMDBConfiguration(
    @SerializedName("images") val images: TMDBImageConfiguration,
    @SerializedName("change_keys") val changeKeys: List<String>
)

data class TMDBImageConfiguration(
    @SerializedName("base_url") val baseUrl: String,
    @SerializedName("secure_base_url") val secureBaseUrl: String,
    @SerializedName("backdrop_sizes") val backdropSizes: List<String>,
    @SerializedName("logo_sizes") val logoSizes: List<String>,
    @SerializedName("poster_sizes") val posterSizes: List<String>,
    @SerializedName("profile_sizes") val profileSizes: List<String>,
    @SerializedName("still_sizes") val stillSizes: List<String>
)

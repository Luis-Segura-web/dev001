package com.iptv.player.data.remote.api

import com.iptv.player.data.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key") apiKey: String
    ): Response<TMDBConfiguration>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Response<TMDBMovieDetails>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<TMDBCredits>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Response<TMDBVideosResponse>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<TMDBImagesResponse>

    @GET("tv/{tv_id}")
    suspend fun getTVDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Response<TMDBTVDetails>

    @GET("tv/{tv_id}/credits")
    suspend fun getTVCredits(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Response<TMDBCredits>

    @GET("tv/{tv_id}/videos")
    suspend fun getTVVideos(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Response<TMDBVideosResponse>

    @GET("tv/{tv_id}/images")
    suspend fun getTVImages(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Response<TMDBImagesResponse>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): Response<TMDBSearchResponse<TMDBMovieSearchResult>>

    @GET("search/tv")
    suspend fun searchTV(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false
    ): Response<TMDBSearchResponse<TMDBTVSearchResult>>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<TMDBSearchResponse<TMDBMovieSearchResult>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<TMDBSearchResponse<TMDBMovieSearchResult>>

    @GET("tv/popular")
    suspend fun getPopularTV(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<TMDBSearchResponse<TMDBTVSearchResult>>

    @GET("tv/top_rated")
    suspend fun getTopRatedTV(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<TMDBSearchResponse<TMDBTVSearchResult>>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
        
        // Image sizes
        const val POSTER_SIZE_W185 = "w185"
        const val POSTER_SIZE_W342 = "w342"
        const val POSTER_SIZE_W500 = "w500"
        const val POSTER_SIZE_W780 = "w780"
        const val POSTER_SIZE_ORIGINAL = "original"
        
        const val BACKDROP_SIZE_W300 = "w300"
        const val BACKDROP_SIZE_W780 = "w780"
        const val BACKDROP_SIZE_W1280 = "w1280"
        const val BACKDROP_SIZE_ORIGINAL = "original"
        
        const val PROFILE_SIZE_W45 = "w45"
        const val PROFILE_SIZE_W185 = "w185"
        const val PROFILE_SIZE_H632 = "h632"
        const val PROFILE_SIZE_ORIGINAL = "original"

        fun buildImageUrl(imagePath: String?, size: String = POSTER_SIZE_W500): String? {
            return if (imagePath != null) {
                "$IMAGE_BASE_URL$size$imagePath"
            } else null
        }

        fun buildPosterUrl(posterPath: String?, size: String = POSTER_SIZE_W500): String? {
            return buildImageUrl(posterPath, size)
        }

        fun buildBackdropUrl(backdropPath: String?, size: String = BACKDROP_SIZE_W1280): String? {
            return buildImageUrl(backdropPath, size)
        }

        fun buildProfileUrl(profilePath: String?, size: String = PROFILE_SIZE_W185): String? {
            return buildImageUrl(profilePath, size)
        }

        fun buildYouTubeTrailerUrl(key: String): String {
            return "https://www.youtube.com/watch?v=$key"
        }

        fun buildYouTubeThumbnailUrl(key: String): String {
            return "https://img.youtube.com/vi/$key/maxresdefault.jpg"
        }
    }
}

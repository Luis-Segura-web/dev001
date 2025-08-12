package com.iptv.player.data.remote.api

import com.iptv.player.data.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface XtreamApi {

    @GET("player_api.php")
    suspend fun authenticate(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<XtreamAuthResponse>

    @GET("player_api.php")
    suspend fun getLiveCategories(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_live_categories"
    ): Response<List<Category>>

    @GET("player_api.php")
    suspend fun getLiveStreams(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_live_streams",
        @Query("category_id") categoryId: String? = null
    ): Response<List<LiveStream>>

    @GET("player_api.php")
    suspend fun getVodCategories(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_vod_categories"
    ): Response<List<Category>>

    @GET("player_api.php")
    suspend fun getVodStreams(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_vod_streams",
        @Query("category_id") categoryId: String? = null
    ): Response<List<VodStream>>

    @GET("player_api.php")
    suspend fun getVodInfo(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_vod_info",
        @Query("vod_id") vodId: Int
    ): Response<VodInfo>

    @GET("player_api.php")
    suspend fun getSeriesCategories(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_series_categories"
    ): Response<List<Category>>

    @GET("player_api.php")
    suspend fun getSeries(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_series",
        @Query("category_id") categoryId: String? = null
    ): Response<List<Series>>

    @GET("player_api.php")
    suspend fun getSeriesInfo(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_series_info",
        @Query("series_id") seriesId: Int
    ): Response<SeriesInfo>

    @GET("xmltv.php")
    suspend fun getEPG(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<String>

    @GET("player_api.php")
    suspend fun getShortEPG(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("action") action: String = "get_short_epg",
        @Query("stream_id") streamId: Int,
        @Query("limit") limit: Int = 10
    ): Response<Map<String, List<EpgProgram>>>

    companion object {
        fun buildStreamUrl(
            serverUrl: String,
            username: String,
            password: String,
            streamId: Int,
            type: String, // "live", "movie", "series"
            extension: String = "ts"
        ): String {
            return when (type) {
                "live" -> "$serverUrl/$username/$password/$streamId.$extension"
                "movie" -> "$serverUrl/movie/$username/$password/$streamId.$extension"
                "series" -> "$serverUrl/series/$username/$password/$streamId.$extension"
                else -> "$serverUrl/$username/$password/$streamId.$extension"
            }
        }

        fun buildCatchupUrl(
            serverUrl: String,
            username: String,
            password: String,
            streamId: Int,
            startTimestamp: Long,
            duration: Int = 3600 // 1 hour in seconds
        ): String {
            return "$serverUrl/timeshift/$username/$password/$duration/$startTimestamp/$streamId.ts"
        }
    }
}

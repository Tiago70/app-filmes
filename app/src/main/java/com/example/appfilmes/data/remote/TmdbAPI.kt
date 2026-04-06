package com.example.appfilmes.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): MoviePageDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): MoviePageDto

    @GET("discover/movie")
    suspend fun getDubbedMovies(
        @Query("with_original_language") language: String = "pt",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): MoviePageDto

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "pt-BR"
    ): MovieDetailDto

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("language") language: String = "pt-BR"
    ): GenreListDto
}
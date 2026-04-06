package com.example.appfilmes.data.remote

import com.google.gson.annotations.SerializedName

data class MovieSummaryDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,   // "2024-03-15" — vem como string completa

    @SerializedName("genre_ids")
    val genreIds: List<Int>     // só os IDs, sem nome — por isso precisamos da tabela de gêneros
)

data class MoviePageDto(
    @SerializedName("results")
    val results: List<MovieSummaryDto>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("page")
    val page: Int
)

data class MovieDetailDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("runtime")
    val runtime: Int?,          // em minutos — só existe nesse endpoint

    @SerializedName("genres")
    val genres: List<GenreDto>  // objeto completo — só existe nesse endpoint
)

data class GenreDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)

data class GenreListDto(
    @SerializedName("genres")
    val genres: List<GenreDto>  // resposta do endpoint /genre/movie/list
)
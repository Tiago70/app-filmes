package com.example.appfilmes.data.repository

import com.example.appfilmes.data.local.AppDatabase
import com.example.appfilmes.data.local.FavoriteEntity
import com.example.appfilmes.data.local.MovieWithGenres

class MovieRepository(private val db: AppDatabase) {

    suspend fun getPopularMovies(): List<MovieWithGenres> =
        db.movieDao().getMoviesWithGenresByCategory("lançamento")

    suspend fun getUpcomingMovies(): List<MovieWithGenres> =
        db.movieDao().getMoviesWithGenresByCategory("upcoming")

    suspend fun getDubbedMovies(): List<MovieWithGenres> =
        db.movieDao().getMoviesWithGenresByCategory("dubbed")

    suspend fun getMovieById(id: Int): MovieWithGenres? =
        db.movieDao().getMoviesWithGenres(listOf(id)).firstOrNull()

    suspend fun addFavorite(movieId: Int) =
        db.favoriteDao().addFavorite(FavoriteEntity(movieId))

    suspend fun removeFavorite(movieId: Int) =
        db.favoriteDao().removeFavorite(FavoriteEntity(movieId))

    suspend fun getFavoriteMovies(): List<MovieWithGenres> =
        db.favoriteDao().getFavoriteMovies()
}
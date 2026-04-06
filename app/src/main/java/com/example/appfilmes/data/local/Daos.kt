package com.example.appfilmes.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Transaction
import androidx.room.Delete
import androidx.room.OnConflictStrategy

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE category = :category")
    suspend fun getByCategory(category: String): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<Int>): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCrossRefs(crossRefs: List<MovieGenreCrossRef>)

    @Transaction
    suspend fun insertMoviesWithGenres(
        movies: List<MovieEntity>,
        crossRefs: List<MovieGenreCrossRef>
    ) {
        insertMovies(movies)
        insertCrossRefs(crossRefs)
    }

    @Transaction
    @Query("SELECT * FROM movies WHERE category = :category")
    suspend fun getMoviesWithGenresByCategory(category: String): List<MovieWithGenres>

    @Transaction
    @Query("SELECT * FROM movies WHERE id IN (:ids)")
    suspend fun getMoviesWithGenres(ids: List<Int>): List<MovieWithGenres>
}

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenres(genres: List<GenreEntity>)
}

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun removeFavorite(favorite: FavoriteEntity)

    @Transaction
    @Query("""
        SELECT movies.* FROM movies 
        INNER JOIN favorites ON movies.id = favorites.movieId
    """)
    suspend fun getFavoriteMovies(): List<MovieWithGenres>
}
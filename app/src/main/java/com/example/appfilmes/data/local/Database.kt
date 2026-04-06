package com.example.appfilmes.data.local

import android.content.Context

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Embedded
import androidx.room.Relation
import androidx.room.Junction
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseYear: String,
    val runtimeMinutes: Int?,
    val category: String
)

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)

@Entity(
    tableName = "movie_genre_cross_ref",
    primaryKeys = ["movieId", "genreId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["id"],
            childColumns = ["genreId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MovieGenreCrossRef(
    val movieId: Int,
    val genreId: Int
)

@Entity(
    tableName = "favorites",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FavoriteEntity(
    @PrimaryKey
    val movieId: Int
)

data class MovieWithGenres(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MovieGenreCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    )
    val genres: List<GenreEntity>
)

@Database(
    entities = [
        MovieEntity::class,
        GenreEntity::class,
        MovieGenreCrossRef::class,
        FavoriteEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movies_db"
                )
                    .addCallback(PrepopulateCallback())
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

    private class PrepopulateCallback : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // roda numa thread separada pois não pode bloquear a thread principal
            CoroutineScope(Dispatchers.IO).launch {
                INSTANCE?.let { database ->
                    database.genreDao().insertGenres(SeedData.genres)
                    database.movieDao().insertMoviesWithGenres(
                        SeedData.movies,
                        SeedData.crossRefs
                    )
                }
            }
        }
    }
}
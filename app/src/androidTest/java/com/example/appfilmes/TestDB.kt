package com.example.appfilmes

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appfilmes.data.local.AppDatabase
import com.example.appfilmes.data.local.FavoriteEntity
import com.example.appfilmes.data.local.SeedData
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TestDB {

    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        // cria um banco em memória — some quando o teste termina
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun testSeedAndQuery() = runBlocking {

        // insere os dados de seed manualmente
        db.genreDao().insertGenres(SeedData.genres)
        db.movieDao().insertMoviesWithGenres(SeedData.movies, SeedData.crossRefs)

        // consulta e imprime no Logcat
        val popular = db.movieDao().getMoviesWithGenresByCategory("lançamento")
        Log.d("DatabaseTest", "=== Filmes lançamento ===")
        popular.forEach { movieWithGenres ->
            Log.d("DatabaseTest", "Filme: ${movieWithGenres.movie.title}")
            Log.d("DatabaseTest", "Ano: ${movieWithGenres.movie.releaseYear}")
            Log.d("DatabaseTest", "Gêneros: ${movieWithGenres.genres.joinToString { it.name }}")
            Log.d("DatabaseTest", "---")
        }

        // testa favoritos
        db.favoriteDao().addFavorite(FavoriteEntity(movieId = popular.first().movie.id))

        val favorites = db.favoriteDao().getFavoriteMovies()
        Log.d("DatabaseTest", "=== Favoritos ===")
        favorites.forEach {
            Log.d("DatabaseTest", "Favorito: ${it.movie.title}")
        }

        // assert mínimo para o teste não falhar sem motivo
        assert(popular.isNotEmpty())
    }
}
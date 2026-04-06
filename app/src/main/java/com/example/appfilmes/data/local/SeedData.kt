package com.example.appfilmes.data.local

// data/local/SeedData.kt

object SeedData {

    val genres = listOf(
        GenreEntity(id = 28,  name = "Ação"),
        GenreEntity(id = 12,  name = "Aventura"),
        GenreEntity(id = 16,  name = "Animação"),
        GenreEntity(id = 35,  name = "Comédia"),
        GenreEntity(id = 80,  name = "Crime"),
        GenreEntity(id = 18,  name = "Drama"),
        GenreEntity(id = 14,  name = "Fantasia"),
        GenreEntity(id = 27,  name = "Terror"),
        GenreEntity(id = 878, name = "Ficção científica"),
        GenreEntity(id = 53,  name = "Thriller")
    )

    val movies = listOf(
        MovieEntity(
            id = 1,
            title = "Inception",
            overview = "Um ladrão que rouba segredos corporativos através do uso da tecnologia de compartilhamento de sonhos.",
            posterPath = "inception.jpg",   // nome do arquivo em res/drawable
            releaseYear = "2010",
            runtimeMinutes = 148,
            category = "popular"
        ),
        MovieEntity(
            id = 2,
            title = "Interstellar",
            overview = "Uma equipe de exploradores viaja através de um buraco de minhoca no espaço.",
            posterPath = "interstellar.jpg",
            releaseYear = "2014",
            runtimeMinutes = 169,
            category = "popular"
        ),
        MovieEntity(
            id = 3,
            title = "O Auto da Compadecida",
            overview = "As aventuras de João Grilo e Chicó, dois nordestinos pobres que vivem de trapaças.",
            posterPath = "auto_compadecida.jpg",
            releaseYear = "2000",
            runtimeMinutes = 104,
            category = "dubbed"
        )
        // adicione quantos quiser seguindo o mesmo padrão
    )

    val crossRefs = listOf(
        MovieGenreCrossRef(movieId = 1, genreId = 878),  // Inception → Ficção científica
        MovieGenreCrossRef(movieId = 1, genreId = 28),   // Inception → Ação
        MovieGenreCrossRef(movieId = 2, genreId = 878),  // Interstellar → Ficção científica
        MovieGenreCrossRef(movieId = 2, genreId = 18),   // Interstellar → Drama
        MovieGenreCrossRef(movieId = 3, genreId = 35),   // Auto da Compadecida → Comédia
        MovieGenreCrossRef(movieId = 3, genreId = 18)    // Auto da Compadecida → Drama
    )
}
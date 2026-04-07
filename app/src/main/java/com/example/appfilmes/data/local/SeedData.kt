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
    )

    val movies = listOf(
        MovieEntity(
            id = 1,
            title = "Super Mario Galaxy: O Filme",
            overview = "Depois de salvar o Reino dos Cogumelos, Mario e seus amigos se encontram em uma missão intergaláctica para deter um novo vilão ameaçador.",
            posterPath = "mairo.webp",   // nome do arquivo em res/drawable
            releaseYear = "2026",
            runtimeMinutes = 100,
            category = "lançamento"
        ),
        MovieEntity(
            id = 2,
            title = "Missão refugio",
            overview = "Um ex-assassino de aluguel, agora um homem recluso numa ilha costeira remota, salva uma garota de uma tempestade terrível, colocando os dois na mira do perigo. Forçado a sair da aposentadoria e do isolamento, o homem é obrigado a enfrentar seu passado perigoso para proteger a menina enquanto tenta sobreviver uma jornada tensa repleta de perigos e redenção.",
            posterPath = "missao.webp",
            releaseYear = "2026",
            runtimeMinutes = 100,
            category = "lançamento"
        ),
        MovieEntity(
            id = 3,
            title = "Caminhos do Crime",
            overview = "Um ladrão enigmático realiza assaltos de alto risco ao longo da icônica rodovia 101. Quando ele mira o golpe de sua vida — na esperança de que este seja seu último trabalho —, seu caminho cruza com o de uma corretora de seguros desiludida, forçando os dois a colaborar. Determinado a desvendar o caso, um detetive implacável se aproxima da operação.",
            posterPath = "caminhos.webp",
            releaseYear = "2026",
            runtimeMinutes = 104,
            category = "lançamento"
        )
    )

    val crossRefs = listOf(
        MovieGenreCrossRef(movieId = 1, genreId = 16),
        MovieGenreCrossRef(movieId = 1, genreId = 12),
        MovieGenreCrossRef(movieId = 2, genreId = 28),
        MovieGenreCrossRef(movieId = 2, genreId = 80),
        MovieGenreCrossRef(movieId = 3, genreId = 28),
        MovieGenreCrossRef(movieId = 3, genreId = 80)
    )
}
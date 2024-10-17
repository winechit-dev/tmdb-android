package com.tmdb.discover

import com.tmdb.discover.model.GenreUIModel
import com.tmdb.domain.model.GenreModel
import com.tmdb.domain.model.MovieDetailsModel
import com.tmdb.ui.MovieUIModel

val genresPreview = listOf(
    GenreUIModel(
        id = 1,
        name = "Action",
        selected = true
    ),
    GenreUIModel(
        id = 2,
        name = "Adventure"
    ),
    GenreUIModel(
        id = 3,
        name = "Animation"
    ),
    GenreUIModel(
        id = 4,
        name = "Comedy"
    ),
    GenreUIModel(
        id = 5,
        name = "Crime"
    ),
    GenreUIModel(
        id = 6,
        name = "Documentary"
    )
)

val moviesPreview = listOf(
    MovieUIModel(
        id = 1,
        name = "",
        posterPath = "",
        genreIds = emptyList()
    ),
    MovieUIModel(
        id = 2,
        name = "",
        posterPath = "",
        genreIds = emptyList()
    ),
    MovieUIModel(
        id = 3,
        name = "",
        posterPath = "",
        genreIds = emptyList()
    ),
    MovieUIModel(
        id = 4,
        name = "",
        posterPath = "",
        genreIds = emptyList()
    ),
    MovieUIModel(
        id = 5,
        name = "",
        posterPath = "",
        genreIds = emptyList()
    )
)

val MovieDetailsPreview = MovieDetailsModel(
    adult = false,
    backdropPath = "/jlWk4J1sV1EHgkjhvsN7EdzGvOx.jpg",
    budget = 17500000,
    genres = listOf(
        GenreModel(
            id = 1,
            name = "Action"
        ),
        GenreModel(
            id = 2,
            name = "Adventure"
        ),
        GenreModel(
            id = 3,
            name = "Animation"
        ),
        GenreModel(
            id = 4,
            name = "Comedy"
        ),
    ),
    homepage = "https://www.the-match-factory.com/catalogue/films/the-substance.html",
    id = 933260,
    imdbId = "",
    originalTitle = "The Substance",
    originalLanguage = "",
    overview = "A fading celebrity decides to use a black market drug, a cell-replicating substance that temporarily creates a younger, better version of herself.",
    popularity = 4536.856,
    posterPath = "/lqoMzCcZYEFK729d6qzt349fB4o.jpg",
    releaseDate = "2024-09-07",
    revenue = 29106531,
    runtime = 141,
    status = "",
    tagline = "If you follow the instructions, what could go wrong?",
    title = "The Substance",
    video = false,
    voteAverage = 5.4f,
    voteCount = 568
)
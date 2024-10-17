package com.tmdb.discover

import com.tmdb.discover.model.GenreUIModel
import com.tmdb.ui.MovieUIModel

val genresPreview = listOf(
    GenreUIModel(
        id = "1",
        name = "Action",
        selected = true
    ),
    GenreUIModel(
        id = "2",
        name = "Adventure"
    ),
    GenreUIModel(
        id = "3",
        name = "Animation"
    ),
    GenreUIModel(
        id = "4",
        name = "Comedy"
    ),
    GenreUIModel(
        id = "5",
        name = "Crime"
    ),
    GenreUIModel(
        id = "6",
        name = "Documentary"
    )
)

val moviesPreview = listOf(
    MovieUIModel(
        id = 1,
        posterPath = "",
        genreIds = emptyList()
    ),
    MovieUIModel(
        id = 2,
        posterPath = "",
        genreIds = emptyList()
    ),
    MovieUIModel(
        id = 3,
        posterPath = "",
        genreIds = emptyList()
    ),
    MovieUIModel(
        id = 4,
        posterPath = "",
        genreIds = emptyList()
    ),
    MovieUIModel(
        id = 5,
        posterPath = "",
        genreIds = emptyList()
    )
)
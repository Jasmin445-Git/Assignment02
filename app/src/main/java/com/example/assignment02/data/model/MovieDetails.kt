package com.example.assignment02.data.model

data class MovieDetails(
    val imdbRating: String = "",
    val Title: String = "",
    val Year: String = "",
    val Runtime: String = "",
    val Genre: String = "",
    val Plot: String = "",
    val Poster: String = ""
) {
    // For easy debugging and logging
    override fun toString(): String {
        return "MovieDetails(title='$Title', year='$Year', runtime='$Runtime', genre='$Genre', rating='$imdbRating')"
    }
}

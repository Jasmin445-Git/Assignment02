package com.example.assignment02.data.model

data class Movie(
    val imdbID: String = "",
    val Title: String = "",
    val Year: String = "",
    val Type: String = "",
    val Poster: String = ""
) {
    // For easy debugging and logging
    override fun toString(): String {
        return "Movie(id='$imdbID', title='$Title', year='$Year', type='$Type', poster='$Poster')"
    }
}

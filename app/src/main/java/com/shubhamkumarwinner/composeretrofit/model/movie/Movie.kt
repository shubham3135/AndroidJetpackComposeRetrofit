package com.shubhamkumarwinner.composeretrofit.model.movie

data class Movie(
    val average_rating: Double,
    val backdrop_path: String,
    val created_by: CreatedBy,
    val description: String,
    val id: Int,
    val iso_3166_1: String,
    val iso_639_1: String,
    val name: String,
    val page: Int,
    val poster_path: String,
    val `public`: Boolean,
    val results: List<Result>,
    val revenue: Long,
    val runtime: Int,
    val sort_by: String,
    val total_pages: Int,
    val total_results: Int
)
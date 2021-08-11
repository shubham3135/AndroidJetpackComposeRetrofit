package com.shubhamkumarwinner.composeretrofit.repository

import com.shubhamkumarwinner.composeretrofit.model.movie.Movie
import com.shubhamkumarwinner.composeretrofit.network.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApiService: MovieApiService) {
    fun getMovie(): Flow<Movie> = flow {
        emit(movieApiService.getMovie())
    }.flowOn(Dispatchers.IO)
}
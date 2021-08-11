package com.shubhamkumarwinner.composeretrofit.network

import com.shubhamkumarwinner.composeretrofit.model.movie.Movie
import com.shubhamkumarwinner.composeretrofit.model.movie.Result
import retrofit2.http.GET

private const val API_KEY = "860912a3654aa99aa0d9fb9958adcb31"
interface MovieApiService {
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    @GET("4/list/1?page=1/&api_key=$API_KEY")
    suspend fun getMovie(): Movie
}
package com.shubhamkumarwinner.composeretrofit.network.util

import com.shubhamkumarwinner.composeretrofit.model.movie.Result

sealed class ApiState{
    class Success(val data: List<Result>): ApiState()
    class Failure(val msg: Throwable): ApiState()
    object Loading: ApiState()
    object Empty: ApiState()
}

package com.shubhamkumarwinner.composeretrofit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubhamkumarwinner.composeretrofit.network.util.ApiState
import com.shubhamkumarwinner.composeretrofit.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {
    val response: MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
        getMovie()
    }

    private fun getMovie() = viewModelScope.launch {
        movieRepository.getMovie().onStart {
            response.value = ApiState.Loading
        }.catch {
            response.value = ApiState.Failure(it)
        }.collect {
            response.value = ApiState.Success(it.results)
        }
    }
}
package com.shubhamkumarwinner.composeretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.shubhamkumarwinner.composeretrofit.model.movie.Result
import com.shubhamkumarwinner.composeretrofit.network.util.ApiState
import com.shubhamkumarwinner.composeretrofit.ui.theme.ComposeRetrofitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRetrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(topBar = {
                        TopAppBar(title = { Text(text = "Movies") }
                        )
                    }) {
                        GetData(movieViewModel = movieViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun GetData(movieViewModel: MovieViewModel) {
    when (val result = movieViewModel.response.value) {
        is ApiState.Success -> {
            LazyColumn {
                items(result.data) { response ->
                    MovieItem(movie = response)
                }
            }
        }
        is ApiState.Failure -> {
            Text(text = "${result.msg}")
        }
        is ApiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ApiState.Empty -> {
        }
    }
}

@Composable
fun MovieItem(movie: Result) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "http://image.tmdb.org/t/p/w500/${movie.poster_path}",
                    onExecute = { _, _ -> true },
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.pic)
//                        transformations(CircleCropTransformation())
                    },
                ),
                contentDescription = "",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = movie.title)
                Text(text = movie.original_language)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRetrofitTheme {

    }
}
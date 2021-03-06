package com.shubhamkumarwinner.composeretrofit.di

import com.shubhamkumarwinner.composeretrofit.network.MovieApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideApiService(moshi: Moshi):MovieApiService =
        Retrofit.Builder().run {
            baseUrl(MovieApiService.BASE_URL)
            addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }.create(MovieApiService::class.java)
}
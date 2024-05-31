package com.eskaya.task_sanstech.di

import MatchRepositoryImpl
import com.eskaya.mvvm_application.BuildConfig
import com.eskaya.task_sanstech.data.remote.services.MatchApi
import com.eskaya.task_sanstech.domain.repository.MatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient = OkHttpClient.Builder().addInterceptor {
        val url = it
            .request()
            .url
            .newBuilder()
            .build()
        it.proceed(it.request().newBuilder().url(url).build())
    }.addInterceptor(interceptor).build()


    @Provides
    @Singleton
    fun providePaprikaApi(): MatchApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MatchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMatchRepository(api: MatchApi): MatchRepository {
        return MatchRepositoryImpl(api)
    }

}
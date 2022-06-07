package com.example.restaurant.di


import android.content.Context

import com.example.restaurant.BuildConfig
import com.example.restaurant.api.NetworkApi


import com.example.restaurant.repository.RestaurantRepository
import com.example.restaurant.repository.ResturantImp
import com.google.gson.GsonBuilder


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, factory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(factory)
            .build()

    @Provides
    fun getGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    @Provides
    fun provideOkHttpClient(
        interceptor: ApiInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NetworkApi =
        retrofit.create(NetworkApi::class.java)



    @Provides
    fun provideRestaurantRepository(api:NetworkApi,@ApplicationContext context: Context): ResturantImp =
        RestaurantRepository(
            api,
            context)

}
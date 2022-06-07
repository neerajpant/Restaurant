package com.example.restaurant.di


//@Module
//@InstallIn(SingletonComponent::class)
//class NetworkModule {
//
//    @Provides
//    fun getGsonConverter(): GsonConverterFactory {
//        return GsonConverterFactory.create(GsonBuilder().create())
//    }
//
//    @Provides
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//
//    @Provides
//    fun provideRetrofit(
//        okHttpClient: OkHttpClient,
//        factory: GsonConverterFactory
//    ): Retrofit {
//        return Retrofit.Builder()
//            .client(okHttpClient)
//            .baseUrl(BuildConfig.BASE_URL)
//            .addConverterFactory(factory)
//            .build()
//    }
//
//    @Provides
//    fun provideOkHttpClient(
//        interceptor: ApiInterceptor,
//        loggingInterceptor: HttpLoggingInterceptor
//    ): OkHttpClient {
//        return OkHttpClient().newBuilder()
//            .addInterceptor(interceptor)
//            .apply {
//                if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
//            }
//            .build()
//    }
//
//    @Provides
//    fun provideNewsApi(retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)
//}
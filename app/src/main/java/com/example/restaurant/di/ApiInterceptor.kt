package com.example.restaurant.di




import com.example.restaurant.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url
            .newBuilder()
            .addQueryParameter("apiKey",BuildConfig.API_KEY)
            .build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
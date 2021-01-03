package com.veldic.ingyeogg.di

import com.squareup.moshi.Moshi
import com.veldic.ingyeogg.BuildConfig
import com.veldic.ingyeogg.network.service.MatchService
import com.veldic.ingyeogg.network.service.SummonerService
import com.veldic.ingyeogg.network.service.retrofit.MatchRetrofitService
import com.veldic.ingyeogg.network.service.retrofit.SummonerRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    private val baseUrl = "https://kr.api.riotgames.com"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val apiTokenInterceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val token = ""
                val request = chain.request()
                return chain.proceed(request.newBuilder().header("X-Riot-Token", token).build())

            }
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiTokenInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()

    @Provides
    @Singleton
    fun provideSummonerService(retrofit: Retrofit): SummonerService {
        return SummonerService(retrofit.create(SummonerRetrofitService::class.java))
    }

    @Provides
    @Singleton
    fun provideMatchService(retrofit: Retrofit): MatchService {
        return MatchService(retrofit.create(MatchRetrofitService::class.java))
    }

}

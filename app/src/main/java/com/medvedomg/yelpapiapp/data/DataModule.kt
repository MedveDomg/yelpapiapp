package com.medvedomg.yelpapiapp.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

internal val dataModule = module {

    single {
        val builder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
        builder.build()
    }

    factory {
        Moshi.Builder().build()
    }

    single {
        val okHttpClient: OkHttpClient = get()
        val moshi: Moshi = get()
        val converterFactory = MoshiConverterFactory.create(moshi)
        Retrofit.Builder()
            .baseUrl("https://api.yelp.com/v3/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    single<BusinessListService> {
        val retrofit: Retrofit = get()
        retrofit.create(BusinessListService::class.java)
    }

    single<BusinessSearchRepository> {
        BusinessSearchRepositoryImpl(
            retrofitService = get()
        )
    }
}
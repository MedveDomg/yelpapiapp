package com.medvedomg.yelpapiapp.data

import com.medvedomg.yelpapiapp.data.Const.BEARER_TOKEN
import retrofit2.http.*

interface BusinessListService {

    @GET("businesses/search")
    suspend fun searchBusinessList(
        @Header("Authorization") authHeader: String = "Bearer $BEARER_TOKEN",
        @Query("term") term: String,
        @Query("location") location: String,
    ): SearchBusinessResponse
}
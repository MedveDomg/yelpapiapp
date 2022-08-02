package com.medvedomg.yelpapiapp.data

import com.medvedomg.yelpapiapp.data.Const.LOCATION_VALUE

interface BusinessSearchRepository {

    suspend fun getBeerBusinessList(term: String): SearchBusinessResponse
}

class BusinessSearchRepositoryImpl(
    val retrofitService: BusinessListService
) : BusinessSearchRepository {

    override suspend fun getBeerBusinessList(term: String): SearchBusinessResponse {
        return retrofitService.searchBusinessList(
            term = term,
            location = LOCATION_VALUE
        )
    }
}
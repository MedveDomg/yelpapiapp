package com.medvedomg.yelpapiapp.domain

import com.medvedomg.yelpapiapp.data.BusinessSearchRepository
import com.medvedomg.yelpapiapp.data.SearchBusinessResponse
import com.medvedomg.yelpapiapp.domain.util.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetBusinessListUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    val businessSearchRepository: BusinessSearchRepository
) : CoroutineUseCase<String, SearchBusinessResponse>(coroutineDispatcher) {

    override suspend fun execute(parameters: String): SearchBusinessResponse {
        return businessSearchRepository.getBeerBusinessList(parameters)
    }
}
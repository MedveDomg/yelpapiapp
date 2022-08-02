package com.medvedomg.yelpapiapp

import com.medvedomg.yelpapiapp.data.*
import com.medvedomg.yelpapiapp.domain.BusinessInteractor
import com.medvedomg.yelpapiapp.domain.GetBusinessListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import com.medvedomg.yelpapiapp.domain.util.Result
import io.mockk.coEvery
import io.mockk.mockk

const val TERM_PIZZA = "pizza"

class GetBusinessListUseCaseTest : Spek({
    Feature(BusinessInteractor::class.java.simpleName) {
        Scenario("when repository returns not correct object") {
            var result: Result<SearchBusinessResponse> = runBlocking {
                GetBusinessListUseCase(
                    coroutineDispatcher = Dispatchers.Default,
                    businessSearchRepository = mockErrorRepository()
                ).invoke(TERM_PIZZA)
            }
            Then("should receive Error") {
                Assert.assertTrue("result: $result", result is Result.Error)
            }
        }
        Scenario("when repository returns proper object") {
            lateinit var useCase: GetBusinessListUseCase
            Given("usecase with success") {
                useCase = GetBusinessListUseCase(
                    coroutineDispatcher = Dispatchers.Default,
                    businessSearchRepository = mockOkRepository()
                )
            }
            Then("should receive success") {
                runBlocking {
                    val result = useCase.invoke("pizza")
                    Assert.assertTrue("result: $result", result is Result.Success)
                }
            }
        }
    }
})

private fun mockErrorRepository(): BusinessSearchRepository {
    val errorRepository = mockk<BusinessSearchRepository>()
    coEvery {
        errorRepository.getBeerBusinessList(
            TERM_PIZZA
        )
    }.throws(Throwable("404 exception"))
    return errorRepository
}

private fun mockOkRepository(): BusinessSearchRepository {
    val errorRepository = mockk<BusinessSearchRepository>()
    coEvery {
        errorRepository.getBeerBusinessList(
            TERM_PIZZA
        )
    } returns SearchBusinessResponse(
        listOf(
            BusinessResponse(
                "1asd",
                "pizzaking",
                imageUrl = "someime",
                isClosed = true,
                locationResponse = null
            )
        ),
        total = 0,
        region = RegionResponse(
            center = CenterResponse(longitude = 123.13, latitude = 123.13)
        )
    )
    return errorRepository
}
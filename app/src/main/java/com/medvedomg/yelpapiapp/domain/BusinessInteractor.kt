package com.medvedomg.yelpapiapp.domain

import com.medvedomg.yelpapiapp.presentation.businesslist.BusinessModel
import com.medvedomg.yelpapiapp.domain.util.Result

class BusinessInteractor(
    private val getBusinessListUseCase: GetBusinessListUseCase,
) {

    suspend fun getBusinessList(): Result<List<BusinessModel>> {
        val pizzaBusinessListResult = getBusinessListUseCase(parameters = "pizza")
        val beerBusinessListResult = getBusinessListUseCase(parameters = "beer")
        return when {
            pizzaBusinessListResult is Result.Success && beerBusinessListResult is Result.Success -> {
                val pizzaBusiness = pizzaBusinessListResult.data.businesses
                val beerBusiness = beerBusinessListResult.data.businesses
                val models = (pizzaBusiness + beerBusiness).map {
                    BusinessModel(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.imageUrl,
                        isClosed = it.isClosed,
                        location = it.locationResponse?.address.orEmpty()
                    )
                }
                Result.Success(models)
            }
            pizzaBusinessListResult is Result.Error && beerBusinessListResult is Result.Error -> {
                Result.Error(Throwable("Data is not valid"))
            }
            else -> Result.Error(Throwable("Oops"))
        }
    }
}
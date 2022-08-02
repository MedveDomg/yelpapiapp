package com.medvedomg.yelpapiapp.presentation.businesslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medvedomg.yelpapiapp.domain.BusinessInteractor
import com.medvedomg.yelpapiapp.domain.util.Result
import com.medvedomg.yelpapiapp.presentation.util.ViewState
import com.medvedomg.yelpapiapp.presentation.util.asLiveData
import kotlinx.coroutines.launch

class BusinessListViewModel(
    private val interactor: BusinessInteractor
) : ViewModel() {

    private val _viewStateLiveData =
        MutableLiveData<ViewState<List<BusinessModel>>>(ViewState.Loading)
    val viewStateLiveData = _viewStateLiveData.asLiveData()

    init {
        viewModelScope.launch {
            when (val result = interactor.getBusinessList()) {
                is Result.Success -> {
                    val viewState = ViewState.Success(result.data)
                    _viewStateLiveData.postValue(viewState)
                }
                is Result.Error -> {
                    val viewState = ViewState.Error<List<BusinessModel>>(
                        error = result.error,
                        errorMessage = result.error.message
                    )
                    _viewStateLiveData.postValue(viewState)
                }
            }
        }
    }
}
package com.medvedomg.yelpapiapp.presentation

import com.medvedomg.yelpapiapp.presentation.businesslist.BusinessListViewModel
import com.medvedomg.yelpapiapp.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainViewModel()
    }

    viewModel {
        BusinessListViewModel(interactor = get())
    }
}
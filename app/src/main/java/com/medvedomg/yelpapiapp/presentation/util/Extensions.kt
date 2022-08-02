package com.medvedomg.yelpapiapp.presentation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun <T> SingleLiveEvent<T>.asLiveData() = this as LiveData<T>
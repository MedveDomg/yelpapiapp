package com.medvedomg.yelpapiapp.presentation.businesslist

data class BusinessModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val isClosed: Boolean,
    val location: String
)
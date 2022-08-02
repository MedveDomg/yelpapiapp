package com.medvedomg.yelpapiapp.data

import com.squareup.moshi.Json

data class SearchBusinessResponse(
    @field:Json(name = "businesses")
    val businesses: List<BusinessResponse>,
    @field:Json(name = "total")
    val total: Long?,
    @field:Json(name = "region")
    val region: RegionResponse
)

data class BusinessResponse(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "image_url")
    val imageUrl: String,
    @field:Json(name = "is_closed")
    val isClosed: Boolean,
    @field:Json(name = "location")
    val locationResponse: LocationResponse?
)

data class LocationResponse(
    @field:Json(name = "address1")
    val address: String?
)

data class RegionResponse(
    @field:Json(name = "center")
    val center: CenterResponse
)

data class CenterResponse(
    @field:Json(name = "longitude")
    val longitude: Double,
    @field:Json(name = "latitude")
    val latitude: Double,
)
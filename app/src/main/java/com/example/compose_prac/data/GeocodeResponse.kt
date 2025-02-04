package com.example.compose_prac.data

import com.google.gson.annotations.SerializedName

data class GeocodeResponse(
    @SerializedName("results") val results: List<GeocodeResult>,
    @SerializedName("status") val status: String
)

data class GeocodeResult(
    @SerializedName("address_components") val addressComponents: List<AddressComponent>,
    @SerializedName("formatted_address") val formattedAddress: String,
    @SerializedName("geometry") val geometry: Geometry,
    @SerializedName("place_id") val placeId: String,
    @SerializedName("plus_code") val plusCode: PlusCode?,
    @SerializedName("types") val types: List<String>
)

data class AddressComponent(
    @SerializedName("long_name") val longName: String,
    @SerializedName("short_name") val shortName: String,
    @SerializedName("types") val types: List<String>
)

data class Geometry(
    @SerializedName("location") val location: LocationData,
    @SerializedName("location_type") val locationType: String,
    @SerializedName("viewport") val viewport: Viewport
)

data class LocationData(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)

data class Viewport(
    @SerializedName("northeast") val northeast: LocationData,
    @SerializedName("southwest") val southwest: LocationData
)

data class PlusCode(
    @SerializedName("compound_code") val compoundCode: String,
    @SerializedName("global_code") val globalCode: String
)

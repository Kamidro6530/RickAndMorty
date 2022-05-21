package com.example.rickandmortycompose.retrofit

import com.example.rickandmortycompose.retrofit.locations.LocationList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface LocationService {

    @GET
   suspend fun getLocations(@Url url: String): Response<LocationList>
}